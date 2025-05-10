package com.example.assignmentm.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.assignmentm.navigation.NavGraph
import com.example.assignmentm.presentation.ui.theme.HiltMVVMComposeMovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HiltMVVMComposeMovieTheme  {
                NavGraph()
            }
        }
    }
}