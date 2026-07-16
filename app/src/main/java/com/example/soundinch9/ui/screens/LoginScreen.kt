package com.example.soundinch9.ui.screens

import androidx.compose.animation.EnterTransition.Companion.None
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "SoundIn") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

    ) { paddingValues ->
        LoginContent(
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
            scope = scope,
            onNavigateToRegister = onNavigateToRegister
        )
    }

}

@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onNavigateToRegister: () -> Unit,

    ) {
    // Local State -- Will move to ViewModel later
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberSession by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues) // Avoids TopAppBar overlap
            .verticalScroll(rememberScrollState()) // Allows for scrolling on small screens
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "S",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "SoundIn",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = false },
            label = { Text("Email Address") },
            isError = emailError,
            supportingText = {
                if (emailError) {
                    Text(text = "Invalid Email Address")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()

        ) // End OutlinedTextField
        // Password Field challenge
        OutlinedTextField(
            value = password,
            onValueChange = { password = it; passwordError = false },
            label = { Text("Password") },
            isError = passwordError,
            supportingText = {
                if (passwordError) {
                    Text(text = "Minimum 6 characters")
                }
            },
            visualTransformation = (if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation(mask = '*')
            }),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        ) // End OutlinedTextField (Password)
        // -- Remember Me  Switch
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Remember Session",
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = rememberSession,
                onCheckedChange = { rememberSession = it }
            )
        } // End Row (Remember Me)
        // Login Button with simple validation
        Button(
            onClick = {
                emailError = !email.contains('@') || !email.contains('.')
                passwordError = password.length < 6
                if (emailError || passwordError) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Please review the marked fields",
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Short

                        )
                    }
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Welcome to SoundIn",
                            actionLabel = "Done",
                            duration = SnackbarDuration.Short
                        )

                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = "Login") }
        // Register Button
        TextButton(onClick = onNavigateToRegister) {
            Text(text = "Don't have an account? Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    LoginContent(
        paddingValues = PaddingValues(),
        snackbarHostState = SnackbarHostState(),
        scope = rememberCoroutineScope(),
        onNavigateToRegister = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onNavigateToRegister = {})
}













