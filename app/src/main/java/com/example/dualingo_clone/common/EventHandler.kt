package com.example.dualingo_clone.common

interface EventHandler<E> {
    fun obtainEvent(event: E)
}