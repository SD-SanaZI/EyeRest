package com.sanazi.eyerest.screens.exercises.exercise4

enum class Exercise4States(val duration: Int = 0) {
    PREPARE(60),
    UP(2), AFTER_UP(2),
    DOWN(2), AFTER_DOWN(2),
    LEFT(2), AFTER_LEFT(2),
    RIGHT(2), AFTER_RIGHT(2),
}