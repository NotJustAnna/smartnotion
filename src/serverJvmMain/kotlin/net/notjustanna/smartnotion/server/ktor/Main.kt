package net.notjustanna.smartnotion.server.ktor

import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.content.resources
import io.ktor.http.content.static
import net.notjustanna.smartnotion.server.ktor.routes.indexRoute

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            indexRoute()
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}
