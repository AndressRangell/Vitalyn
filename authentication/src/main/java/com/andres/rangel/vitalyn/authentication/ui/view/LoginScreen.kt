package com.andres.rangel.vitalyn.authentication.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andres.rangel.vitalyn.authentication.R
import com.andres.rangel.vitalyn.authentication.ui.state.LoginEvent
import com.andres.rangel.vitalyn.authentication.ui.viewmodel.LoginViewModel
import com.andres.rangel.vitalyn.core.ui.theme.BlueFacebook
import com.andres.rangel.vitalyn.core.ui.theme.VitalynTheme
import com.andres.rangel.vitalyn.core.ui.theme.White

@Composable
fun LoginScreen(
    navigateToSports: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        loginViewModel.loginEvent.collect { event ->
            when (event) {
                is LoginEvent.LoggedInSuccessfully -> {
                    navigateToSports()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginHeader()
        Spacer(modifier = Modifier.height(32.dp))
        LoginForm(
            loginViewModel = loginViewModel,
            onLoginClick = { loginViewModel.login() },
            onForgotPasswordClick = {},
            onCreateAccountClick = {}
        )
    }
}

@Composable
private fun LoginHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Vitalyn",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "¡Bienvenido!",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun LoginForm(
    loginViewModel: LoginViewModel,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    val loginFormState by loginViewModel.loginFormState.collectAsState()

    val isFormValid = !loginFormState.isLoading &&
            loginFormState.email.isNotBlank() &&
            loginFormState.password.isNotBlank()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginTextField(
                value = loginFormState.email,
                onValueChange = { loginViewModel.onEmailChange(it) },
                label = "Correo Electrónico",
                isPassword = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            LoginTextField(
                value = loginFormState.password,
                onValueChange = { loginViewModel.onPasswordChange(it) },
                label = "Contraseña",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (loginFormState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            LoginButton(
                onClick = onLoginClick,
                text = "Iniciar Sesión",
                enabled = isFormValid
            )
            Spacer(modifier = Modifier.height(10.dp))
            SocialLoginSection({}, {})
            Spacer(modifier = Modifier.height(10.dp))
            ErrorMessage(loginFormState.errorMessage)
            Spacer(modifier = Modifier.height(10.dp))
            LoginSecondaryActions(
                onForgotPasswordClick = onForgotPasswordClick,
                onCreateAccountClick = onCreateAccountClick
            )
        }
    }
}

@Composable
private fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = if (passwordVisible) {
                            painterResource(id = R.drawable.ic_visibility_off)
                        } else {
                            painterResource(id = R.drawable.ic_visibility)
                        },
                        contentDescription = if (passwordVisible) {
                            "Ocultar contraseña"
                        } else {
                            "Mostrar contraseña"
                        }
                    )
                }
            }
        } else null
    )
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.4f)
    ),
    enabled: Boolean = true
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        onClick = {
            keyboardController?.hide()
            onClick()
        },
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = MaterialTheme.shapes.large,
        colors = colors,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ErrorMessage(
    message: String = ""
) {
    AnimatedVisibility(
        visible = message.isNotBlank(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 34.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun LoginSecondaryActions(
    onForgotPasswordClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = onForgotPasswordClick) {
            Text("¿Olvidaste tu Contraseña?")
        }

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedButton(
            onClick = onCreateAccountClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Crear Cuenta",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun SocialLoginSection(
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("ó")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onGoogleClick,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(20.dp),
                    tint = Color.Unspecified
                )
                Text("Continuar con Google")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onFacebookClick,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueFacebook,
                contentColor = White
            )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Google",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(20.dp),
                    tint = Color.Unspecified
                )
                Text("Continuar con Facebook")
            }
        }
    }
}


@Preview
@Composable
fun LoginPreview() {
    VitalynTheme {
        Scaffold {
            Box(modifier = Modifier.padding(it))
            LoginScreen({})
        }
    }
}