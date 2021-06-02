package net.notjustanna.smartnotion.server.ktor

import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.notjustanna.smartnotion.server.ktor.routes.indexRoute
import net.notjustanna.smartnotion.service.DefaultEventHandler
import net.notjustanna.smartnotion.service.EventDispatcher
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        di {
            bindSingleton { DefaultEventHandler() }
            bindSingleton { EventDispatcher(directDI.instance<DefaultEventHandler>().asConsumer()) }
        }
        routing {
            indexRoute()
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}
