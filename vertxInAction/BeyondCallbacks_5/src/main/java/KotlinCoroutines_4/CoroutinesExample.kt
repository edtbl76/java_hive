package KotlinCoroutines_4

import kotlinx.coroutines.*;

/*
    We wrap main() in runBlocking, because all of the methods being
    called are "suspending"
        - i.e. their execution must wait for al coroutines to complete.
 */
fun main() = runBlocking {

    // This starts a job.
    val job1 = launch { delay(500) }
    fun fib(n: Long): Long = if (n < 2) n else fib(n - 1) + fib(n - 2)

    // starts a job that will return a value (async blocks return a value)
    val job2 = async { fib(42)}

    // waits for job to complete
    job1.join()
    println("job1 is done");

    // get value when job is done. (await is used to wait for the async jobs to finish so we can return the value)
    println("job2 fib(42) =  ${job2.await()}")

}

