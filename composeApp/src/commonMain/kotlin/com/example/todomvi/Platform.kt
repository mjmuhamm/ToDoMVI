package com.example.todomvi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform