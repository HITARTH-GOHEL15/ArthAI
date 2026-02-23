from fastapi import FastAPI
from pydantic import BaseModel
from google import genai
import os
import json
import re
import requests
from dotenv import load_dotenv

load_dotenv()

app = FastAPI(title="Arth AI")

client = genai.Client(api_key=os.getenv("GOOGLE_API_KEY"))

GITHUB_HEADERS = {
    "Accept": "application/vnd.github+json",
    "User-Agent": "ArthAI"
}

# ==========================
# MODELS
# ==========================


class AnalyzeRepository(BaseModel):
    repo_link: str


class CompareRepositories(BaseModel):
    repo_link_1: str
    repo_link_2: str


# ==========================
# HEALTH CHECK
# ==========================

@app.get("/")
def health():
    return {"status": "Arth AI running"}


# ==========================
# UTIL FUNCTIONS
# ==========================

def extract_repo_name(repo_link: str) -> str:
    pattern = r"github\.com/([^/]+/[^/]+)"
    match = re.search(pattern, repo_link)

    if not match:
        raise ValueError("Invalid GitHub repository link")

    return match.group(1)


def fetch_github_repo_data(repo_name: str):
    url = f"https://api.github.com/repos/{repo_name}"

    response = requests.get(url, headers=GITHUB_HEADERS, timeout=10)

    if response.status_code != 200:
        raise ValueError("Repository not found or GitHub API error")

    data = response.json()

    return {
        "name": data.get("name"),
        "description": data.get("description"),
        "stars": data.get("stargazers_count"),
        "forks": data.get("forks_count"),
        "language": data.get("language"),
        "topics": data.get("topics"),
        "open_issues": data.get("open_issues_count"),
        "created_at": data.get("created_at"),
        "updated_at": data.get("updated_at")
    }


def fetch_readme(repo_name: str):
    url = f"https://raw.githubusercontent.com/{repo_name}/main/README.md"

    try:
        response = requests.get(url, timeout=10)

        if response.status_code == 200:
            return response.text[:8000]

        return "README not available."

    except:
        return "README fetch failed."


def fetch_contributors(repo_name: str):
    url = f"https://api.github.com/repos/{repo_name}/contributors"

    try:
        response = requests.get(url, headers=GITHUB_HEADERS, timeout=10)

        if response.status_code != 200:
            return []

        data = response.json()

        return [
            {
                "login": c.get("login"),
                "contributions": c.get("contributions")
            }
            for c in data[:10]
        ]

    except:
        return []


# ==========================
# GEMINI ANALYSIS
# ==========================

def analyze_repository_with_gemini(repo_data, readme, contributors):

    prompt = f"""
You are a senior software architect.

Repository Metadata:
{json.dumps(repo_data, indent=2)}

Top Contributors:
{json.dumps(contributors[:5], indent=2)}

README Content:
{readme[:4000]}

Analyze deeply and return STRICT JSON:

{{
  "summary": "Short explanation",
  "target_audience": "Who is this project for?",
  "tech_stack": ["Technologies used"],
  "complexity": "Beginner | Intermediate | Advanced",
  "use_cases": ["Main use cases"],
  "strengths": ["Strengths"],
  "improvement_suggestions": ["Improvements"],
  "community_strength": "Low | Medium | High",
  "maintenance_risk": "Low | Medium | High",
  "ai_score": 0-10,
  "confidence_score": "Low | Medium | High"
}}

Return ONLY valid JSON.
"""

    response = client.models.generate_content(
        model="gemini-flash-latest",
        contents=prompt,
        config={
            "temperature": 0.2,
            "max_output_tokens": 2000
        }
    )

    text = response.text.strip()

    try:
        return json.loads(text)
    except:
        return {
            "summary": text,
            "error": "AI response not structured properly"
        }


# ==========================
# ANALYZE REPO ENDPOINT
# ==========================

@app.post("/analyze-repo")
def analyze_repo(request: AnalyzeRepository):
    try:
        repo_name = extract_repo_name(request.repo_link)

        repo_data = fetch_github_repo_data(repo_name)
        readme = fetch_readme(repo_name)
        contributors = fetch_contributors(repo_name)

        analysis = analyze_repository_with_gemini(
            repo_data,
            readme,
            contributors
        )

        return {
            "status": "success",
            "repository": repo_name,
            "github_data": repo_data,
            "analysis": analysis
        }

    except ValueError as ve:
        return {"status": "error", "message": str(ve)}

    except Exception as e:
        return {"status": "error", "message": str(e)}


# ==========================
# COMPARE REPOSITORIES
# ==========================

@app.post("/compare-repos")
def compare_repos(request: CompareRepositories):
    try:
        repo1 = extract_repo_name(request.repo_link_1)
        repo2 = extract_repo_name(request.repo_link_2)

        data1 = fetch_github_repo_data(repo1)
        data2 = fetch_github_repo_data(repo2)

        prompt = f"""
Compare these two repositories.

Repo 1:
{json.dumps(data1, indent=2)}

Repo 2:
{json.dumps(data2, indent=2)}

Return STRICT JSON:

{{
  "better_for_beginners": "repo name",
  "more_scalable": "repo name",
  "stronger_community": "repo name",
  "overall_winner": "repo name",
  "comparison_summary": "Short explanation"
}}

Return ONLY JSON.
"""

        response = client.models.generate_content(
            model="gemini-flash-latest",
            contents=prompt,
            config={
                "temperature": 0.2,
                "max_output_tokens": 1500
            }
        )

        text = response.text.strip()

        try:
            comparison = json.loads(text)
        except:
            comparison = {"raw_response": text}

        return {
            "status": "success",
            "comparison": comparison
        }

    except Exception as e:
        return {"status": "error", "message": str(e)}
