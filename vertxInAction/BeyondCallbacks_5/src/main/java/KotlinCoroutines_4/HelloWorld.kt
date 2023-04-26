package KotlinCoroutines_4

import kotlinx.coroutines.*

// This function may be suspended
suspend fun hello(): String {

    // Since this function is suspending, it won't block the caller thread.
    delay(1000)
    return "Hello!"
}

fun main() {
    // This allows waiting for coroutine code to complete.
    runBlocking {
        print(hello())
    }
}
