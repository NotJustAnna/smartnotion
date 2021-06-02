package net.notjustanna.smartnotion.service

import kotlinx.coroutines.*
import java.io.Closeable
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors

class EventDispatcher<T>(private val consumer: (T) -> Unit) : Closeable {
    private val dispatcher = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    ).asCoroutineDispatcher()

    private val jobs = ConcurrentHashMap<UUID, Job>()

    fun dispatch(event: T) {
        consumer(event)
    }

    fun cancelDispatch(uuid: UUID) = jobs[uuid]?.let { runBlocking { it.cancelAndJoin() } }

    fun scheduleDispatch(event: T, timeMillis: Long): UUID {
        val response = CompletableDeferred<UUID>()

        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch(dispatcher) {
            val jobUUID = putAndGet(this.coroutineContext.job)
            response.complete(jobUUID)

            try {
                delay(timeMillis)
                if (isActive) {
                    dispatch(event)
                }
            } finally {
                jobs.remove(jobUUID)
            }
        }

        return runBlocking { response.await() }
    }

    private fun putAndGet(value: Job): UUID {
        var key: UUID
        do {
            key = UUID.randomUUID()
        } while (jobs.putIfAbsent(key, value) != null)
        return key
    }

    override fun close() {
        dispatcher.close()
    }
}
