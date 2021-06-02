package net.notjustanna.smartnotion.server.ktor

import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.notjustanna.smartnotion.server.ktor.routes.indexRoute
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        di {

        }
        routing {
            indexRoute()
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}
