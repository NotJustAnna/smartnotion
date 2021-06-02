package net.notjustanna.smartnotion.service

sealed class Event {
    data class Update(val id: Long) : Event()

    interface Handler<T> {
        fun handle(event: Update): T

        fun asConsumer(): (Event) -> T = {
            when (it) {
                is Update -> handle(it)
            }
        }
    }
}
