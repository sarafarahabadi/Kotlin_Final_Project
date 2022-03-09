package com.example.myapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


const val HOME_ROUTE = "home"
const val NOTE_ROUTE = "note"

@Composable
fun MainView() {
    val userVM = viewModel<UserViewModel>()

    if(userVM.username.value.isEmpty()) {
        LoginView(userVM)
    } else {
        MainScaffoldView()
    }
}

@Composable
fun MainScaffoldView() {

    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBarView() },
        bottomBar = { BottomBarView(navController) },
        content = { MainContentView(navController) }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable( route = HOME_ROUTE ){ HomeView() }
        composable( route = NOTE_ROUTE ){ NoteView() }
    }
}

@Composable
fun HomeView() {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFB6A266))){

    }
}

@Composable
fun NoteView() {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF796C44))){

    }
}

@Composable
fun BottomBarView(navController: NavHostController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFF7CA43)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "home",
            modifier = Modifier.clickable { navController.navigate(HOME_ROUTE)}
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_grocery),
            contentDescription = "note",
            modifier = Modifier.clickable { navController.navigate(NOTE_ROUTE)}
        )
    }
}

@Composable
fun TopBarView() {
    val userVM = viewModel<UserViewModel>()
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFF7CA43))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = userVM.username.value)
        OutlinedButton(onClick = { userVM.logoutUser() }) {
            Text(text = "Log out")
        }
    }
}


@Composable
fun LoginView(userVM: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") })
        OutlinedTextField(
            value = pw,
            onValueChange = { pw = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation())
        OutlinedButton(onClick = { userVM.loginUser(email, pw)}) {
            Text(text = "Login")
        }
    }
}