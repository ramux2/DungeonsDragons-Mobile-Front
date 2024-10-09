package org.example.Util

open class Dices {
    companion object {
        fun d6(): Int = (1..6).random()
        fun d8(): Int = (1..8).random()
        fun d10(): Int = (1..10).random()
        fun d12(): Int = (1..12).random()
    }
}