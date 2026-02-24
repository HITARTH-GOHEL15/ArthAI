<div align="center">

# 🤖 ArthAI

### AI-Powered Repository Intelligence Platform

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.21-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-4285F4?style=flat&logo=jetpackcompose)](https://www.jetbrains.com/compose-multiplatform/)
[![Ktor](https://img.shields.io/badge/Ktor-3.4.0-orange.svg?style=flat&logo=ktor)](https://ktor.io/)
[![FastAPI](https://img.shields.io/badge/FastAPI-Backend-009688?style=flat&logo=fastapi)](https://fastapi.tiangolo.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20iOS%20%7C%20Desktop-lightgrey?style=flat)](https://github.com/YOUR_USERNAME/ArthAI)

*Transform repository analysis with the power of artificial intelligence*

[Features](#-features) • [Screenshots](#-screenshots) • [Installation](#-installation) • [Architecture](#-architecture) • [API](#-api-documentation) • [Contributing](#-contributing)

</div>

---

## 📱 About

**ArthAI** is a cutting-edge mobile application that leverages artificial intelligence to provide deep insights into GitHub repositories. Whether you're evaluating a new library, comparing frameworks, or assessing code quality, ArthAI delivers comprehensive analysis powered by Google Gemini AI.

### 🎯 Why ArthAI?

- **🚀 Save Time**: Get instant AI-powered insights instead of manual repository review
- **📊 Data-Driven**: Make informed decisions based on comprehensive metrics
- **⚖️ Compare Easily**: Side-by-side comparison of repositories
- **🎓 Learn Better**: Understand complexity levels and best practices
- **💡 Improve Quality**: Get actionable suggestions for your projects

---

## ✨ Features

<table>
<tr>
<td width="50%">

### 🔍 Repository Analysis
- **AI Score** (0-100) with confidence level
- **Tech Stack Detection** - Automatic identification
- **Code Quality Metrics** - Comprehensive evaluation
- **Complexity Assessment** - Beginner/Intermediate/Advanced
- **Target Audience** - Who should use this repo
- **Use Cases** - Real-world applications
- **Strengths Analysis** - What makes it great
- **Improvement Suggestions** - AI-powered recommendations

</td>
<td width="50%">

### ⚖️ Repository Comparison
- **Side-by-Side Analysis** - Compare two repos
- **Overall Winner** - AI-determined best choice
- **Better for Beginners** - Learning curve comparison
- **Stronger Community** - Support & activity metrics
- **Scalability Assessment** - Growth potential
- **Detailed Summary** - Comprehensive comparison report

</td>
</tr>
</table>

### 📊 GitHub Integration

- **Live Statistics** - Stars, forks, issues, watchers
- **Language Detection** - Primary programming language
- **Topics/Tags** - Repository categorization
- **Repository Metadata** - Name, description, dates
- **Community Metrics** - Activity and engagement

### 🎨 User Experience

- **Modern UI** - Clean, intuitive interface with ArthAI green theme
- **Dark Mode** - Easy on the eyes
- **Smooth Animations** - Polished user experience
- **Loading States** - Clear feedback during analysis
- **Error Handling** - Graceful error messages

---

## 📸 Screenshots

<div align="center">

### Repository Analysis
<img src="screenshots/analysis.png" width="250" alt="Analysis">

*AI-powered insights with comprehensive metrics*

### Repository Comparison
<img src="screenshots/comparison.png" width="250" alt="Comparison">

*Side-by-side evaluation with winner determination*

</div>

---

## 🛠️ Tech Stack

### Frontend (Mobile App)

| Technology | Purpose | Version |
|-----------|---------|---------|
| **Kotlin Multiplatform** | Shared business logic | 1.9.21 |
| **Compose Multiplatform** | Declarative UI framework | Latest |
| **Ktor Client** | HTTP networking | 3.4.0 |
| **Koin** | Dependency injection | 4.1.1 |
| **Kotlinx Serialization** | JSON parsing | 1.6.3 |
| **Coroutines** | Asynchronous programming | Latest |

### Backend (API Server)

| Technology | Purpose |
|-----------|---------|
| **FastAPI** | Modern Python web framework |
| **Google Gemini AI** | Large language model for analysis |
| **GitHub API** | Repository data fetching |
| **Uvicorn** | ASGI server |
| **Render** | Cloud hosting platform |

### Development Tools

- **Android Studio** - Hedgehog (2023.1.1) or later
- **Gradle** - Build automation
- **Git** - Version control

---

## 🚀 Installation

### Prerequisites

Before you begin, ensure you have:

- ✅ **Android Studio** Hedgehog (2023.1.1) or later
- ✅ **JDK 17** or later
- ✅ **Kotlin 1.9.21** or later
- ✅ **Git** for version control

### Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/ArthAI.git
cd ArthAI
```

### Open in Android Studio

1. **Open Android Studio**
2. **File** → **Open**
3. Select the `ArthAI` folder
4. Wait for Gradle sync to complete

### Run the Application

#### Android
```bash
./gradlew :composeApp:assembleDebug
```
Or click **Run** ▶️ in Android Studio with Android emulator/device selected

#### Desktop
```bash
./gradlew :composeApp:run
```
Or select **Desktop** configuration and click **Run** ▶️

#### iOS
```bash
cd iosApp
pod install
open iosApp.xcworkspace
```
Build and run from Xcode

---

## 🏗️ Project Structure

```
ArthAI/
├── composeApp/                  # Main application module
│   ├── src/
│   │   ├── commonMain/          # Shared code (all platforms)
│   │   │   ├── kotlin/
│   │   │   │   ├── core/        # Theme, colors, utilities
│   │   │   │   ├── data/        # Models, API, repository
│   │   │   │   │   ├── repoAnalyze/
│   │   │   │   │   └── repoCompare/
│   │   │   │   ├── di/          # Dependency injection
│   │   │   │   └── ui/          # UI screens & components
│   │   │   │       ├── repoAnalyze/
│   │   │   │       └── repo_comparison/
│   │   ├── androidMain/         # Android-specific code
│   │   ├── desktopMain/         # Desktop-specific code
│   │   └── iosMain/             # iOS-specific code
│   └── build.gradle.kts
├── gradle/
├── screenshots/                 # App screenshots
├── .gitignore
├── README.md
├── LICENSE
└── build.gradle.kts
```

---

## 🏛️ Architecture

ArthAI follows **Clean Architecture** principles with clear separation of concerns:

### Architecture Layers

```
┌────────────────────────────────────────┐
│           UI Layer (Compose)           │
│  ┌─────────────────────────────────┐   │
│  │ RepoAnalyzeScreen               │   │
│  │ RepoCompareScreen               │   │
│  └─────────────────────────────────┘   │
└──────────────────┬─────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│        ViewModel Layer (State)          │
│  ┌─────────────────────────────────┐   │
│  │ RepoAnalyzeViewModel             │   │
│  │ RepoCompareViewModel             │   │
│  │   - StateFlow                    │   │
│  │   - Loading states               │   │
│  └─────────────────────────────────┘   │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│      Repository Layer (Use Cases)       │
│  ┌─────────────────────────────────┐   │
│  │ ArthRepository                   │   │
│  │   - repoAnalyze()                │   │
│  │   - repoCompare()                │   │
│  └─────────────────────────────────┘   │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│       Data Layer (API & Models)         │
│  ┌─────────────────────────────────┐   │
│  │ KtorArthAPI                      │   │
│  │ RepoAnalyzeModel                 │   │
│  │ RepoCompareModel                 │   │
│  └─────────────────────────────────┘   │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│         Backend API (FastAPI)           │
│          arthai-1.onrender.com          │
└─────────────────────────────────────────┘
```

### Design Patterns

- **MVVM** - Model-View-ViewModel architecture
- **Repository Pattern** - Data access abstraction
- **Dependency Injection** - Using Koin
- **StateFlow** - Reactive state management
- **Single Source of Truth** - ViewModel holds UI state

---

## 📡 API Documentation

### Base URL
```
https://arthai-1.onrender.com
```

### Endpoints

#### 1. Analyze Repository

**Endpoint:** `POST /analyze-repo`

**Request:**
```json
{
  "repo_link": "github.com/facebook/react"
}
```

**Response:**
```json
{
  "status": "success",
  "repository": "github.com/facebook/react",
  "analysis": [
    {
      "ai_score": 92,
      "confidence_score": "High",
      "complexity": "Intermediate",
      "target_audience": "Web Developers",
      "tech_stack": ["JavaScript", "React", "Node.js"],
      "summary": "React is a powerful JavaScript library...",
      "strengths": [
        "Excellent documentation",
        "Large community support",
        "Component-based architecture"
      ],
      "improvement_suggestions": [
        "Add more beginner tutorials",
        "Improve performance in large apps"
      ],
      "use_cases": [
        "Single Page Applications",
        "Progressive Web Apps"
      ],
      "community_strength": "High",
      "maintenance_risk": "Low"
    }
  ],
  "github_data": [
    {
      "name": "react",
      "description": "A JavaScript library for building user interfaces",
      "stars": 220000,
      "forks": 45000,
      "open_issues": 800,
      "language": "JavaScript",
      "topics": ["react", "javascript", "ui", "frontend"],
      "created_at": "2013-05-24",
      "updated_at": "2024-02-24"
    }
  ]
}
```

#### 2. Compare Repositories

**Endpoint:** `POST /compare-repos`

**Request:**
```json
{
  "repo_link_1": "github.com/facebook/react",
  "repo_link_2": "github.com/vuejs/vue"
}
```

**Response:**
```json
{
  "status": "success",
  "comparison": {
    "overall_winner": "Repository 1",
    "comparison_summary": "React offers better performance and larger ecosystem...",
    "better_for_beginners": "Repository 2",
    "stronger_community": "Repository 1",
    "more_scalable": "Repository 1"
  }
}
```

### Error Responses

```json
{
  "status": "error",
  "message": "Repository not found"
}
```

---

## 🎨 Color Palette

ArthAI uses a carefully crafted green-themed dark mode palette:

```kotlin
// Primary Colors
val GreenPrimary = Color(0xFF00C853)      // Vibrant Green
val GreenSecondary = Color(0xFF69F0AE)    // Light Green
val GreenBorder = Color(0xFF00E676)       // Border Green
val GreenDark = Color(0xFF00A843)         // Dark Green

// Background Colors
val NightBlack = Color(0xFF0A0E14)        // Deep Black
val NightSurface = Color(0xFF151B23)      // Surface
val NightCharcoal = Color(0xFF1E2530)     // Charcoal

// Text Colors
val TextWhite = Color(0xFFFFFFFF)         // White
val TextLightGrey = Color(0xFFB8BFC6)     // Light Grey
val TextMediumGrey = Color(0xFF7A8793)    // Medium Grey

// Status Colors
val SuccessGreen = Color(0xFF4CAF50)      // Success
val WarningYellow = Color(0xFFFFB74D)     // Warning
val ErrorRed = Color(0xFFF44336)          // Error
```

---

## 🧪 Testing

### Run Unit Tests

```bash
./gradlew test
```

### Run Android Instrumented Tests

```bash
./gradlew connectedAndroidTest
```

### Test Coverage

```bash
./gradlew jacocoTestReport
```

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### Ways to Contribute

- 🐛 **Report Bugs** - Found a bug? Open an issue
- 💡 **Suggest Features** - Have an idea? We'd love to hear it
- 📝 **Improve Documentation** - Help others understand the project
- 🔧 **Submit Pull Requests** - Fix bugs or add features

### Contribution Process

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes**
4. **Commit with clear messages**
   ```bash
   git commit -m "Add amazing feature"
   ```
5. **Push to your fork**
   ```bash
   git push origin feature/amazing-feature
   ```
6. **Open a Pull Request**

### Code Style

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Add comments for complex logic
- Write unit tests for new features

---

## 📝 Changelog

### Version 1.0.0 (February 2026)

#### ✨ Features
- Repository analysis with AI scoring
- Side-by-side repository comparison
- Tech stack detection
- Strengths and weaknesses analysis
- Improvement suggestions
- GitHub statistics integration
- Cross-platform support (Android, iOS, Desktop)

#### 🎨 Design
- Modern ArthAI green theme
- Dark mode optimized
- Smooth animations and transitions
- Responsive layouts

#### 🔧 Technical
- Kotlin Multiplatform architecture
- Compose Multiplatform UI
- Ktor networking
- Koin dependency injection
- StateFlow state management

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2026 ArthAI Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
```

---

## 👥 Team

### Developers

<table>
<tr>
  <td align="center">
    <a href="https://github.com/HITARTH-GOHEL15">
      <img src="https://github.com/HHITARTH-GOHEL15.png" width="100px;" alt=""/>
      <br />
      <sub><b>Hitarth Gohel</b></sub>
    </a>
    <br />
    <sub>Lead Developer</sub>
  </td>
</tr>
</table>

---

## 🙏 Acknowledgments

- **Google Gemini AI** - Powering intelligent analysis
- **GitHub API** - Repository data source
- **JetBrains** - Kotlin & Compose Multiplatform
- **FastAPI** - Modern Python framework
- **Ktor** - Kotlin networking
- **Koin** - Dependency injection
- **Render** - Cloud hosting

---

## 📞 Contact & Support

### Get in Touch

- 📧 **Email**: hitarthgohel15@gmail.com
- 💼 **LinkedIn**: [Hitarth Gohel](www.linkedin.com/in/hitarthgohel)

### Report Issues

Found a bug or have a suggestion? 

👉 [Open an Issue](https://github.com/HITARTH-GOHEL15/ArthAI/issues)

### Backend API

- **API Docs**: https://arthai-1.onrender.com/docs
- **Status**: https://arthai-1.onrender.com/

---

## ⭐ Show Your Support

If you find this project useful, please consider:

- ⭐ **Starring** this repository
- 🔗 **Sharing** with your network
- 🐛 **Reporting** bugs you find
- 💡 **Suggesting** new features
- 🤝 **Contributing** to the code

---

## 📊 Stats

![GitHub stars](https://img.shields.io/github/stars/HITARTH-GOHEL15/ArthAI?style=social)
![GitHub forks](https://img.shields.io/github/forks/HITARTH-GOHEL15/ArthAI?style=social)
![GitHub issues](https://img.shields.io/github/issues/HITARTH-GOHEL15/ArthAI)
![GitHub pull requests](https://img.shields.io/github/issues-pr/HITARTH-GOHEL15/ArthAI)
![GitHub last commit](https://img.shields.io/github/last-commit/HITARTH-GOHEL15/ArthAI)

---

<div align="center">

### Made with ❤️ using Kotlin Multiplatform

**[⬆ Back to Top](#-arthai)**

</div>
