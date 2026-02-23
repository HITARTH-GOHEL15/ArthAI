package org.example.arthai.ui.Auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.arthai.core.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onSendResetLink: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onSupportClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var isEmailSent by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NightBlack)
    ) {
        // Back button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = TextWhite
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!isEmailSent) {
                // Email input view
                ForgotPasswordForm(
                    email = email,
                    onEmailChange = { email = it },
                    onSendClick = {
                        onSendResetLink(email)
                        isEmailSent = true
                    }
                )
            } else {
                // Email sent confirmation
                EmailSentConfirmation(
                    email = email,
                    onResendClick = {
                        onSendResetLink(email)
                    },
                    onBackToLoginClick = onBackClick
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Footer Links
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "PRIVACY",
                    color = TextMediumGrey,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.clickable(onClick = onPrivacyClick)
                )
                Text(
                    text = "TERMS",
                    color = TextMediumGrey,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.clickable(onClick = onTermsClick)
                )
                Text(
                    text = "SUPPORT",
                    color = TextMediumGrey,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.clickable(onClick = onSupportClick)
                )
            }
        }
    }
}

@Composable
fun ForgotPasswordForm(
    email: String,
    onEmailChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(NightSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                tint = GreenSecondary,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = "Forgot Password?",
            color = TextWhite,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Subtitle
        Text(
            text = "No worries! Enter your email address and we'll send you a link to reset your password.",
            color = TextLightGrey,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Email Address
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "EMAIL ADDRESS",
                color = TextMediumGrey,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "name@company.com",
                        color = TextMediumGrey
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = TextMediumGrey
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextLightGrey,
                    focusedContainerColor = NightSurface,
                    unfocusedContainerColor = NightSurface,
                    focusedBorderColor = GreenBorder,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = GreenSecondary
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Send Reset Link Button
        Button(
            onClick = onSendClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenPrimary,
                contentColor = TextWhite
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Send Reset Link",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun EmailSentConfirmation(
    email: String,
    onResendClick: () -> Unit,
    onBackToLoginClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Success Icon
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(NightSurface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "✓",
                color = SuccessGreen,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = "Check Your Email",
            color = TextWhite,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Message
        Text(
            text = "We've sent a password reset link to",
            color = TextLightGrey,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = email,
            color = GreenSecondary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Click the link in the email to reset your password.",
            color = TextLightGrey,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Back to Login Button
        Button(
            onClick = onBackToLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenPrimary,
                contentColor = TextWhite
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Back to Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Resend link
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Didn't receive the email? ",
                color = TextLightGrey,
                fontSize = 14.sp
            )
            Text(
                text = "Resend",
                color = GreenSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(onClick = onResendClick)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Email tips
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = NightSurface
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "💡 Tips",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "• Check your spam or junk folder\n• Make sure you entered the correct email\n• Wait a few minutes for the email to arrive",
                    color = TextLightGrey,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}