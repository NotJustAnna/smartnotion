package net.notjustanna.smartnotion.server.ktor.routes

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.indexRoute() {
    get("/") {
        call.respondHtml(HttpStatusCode.OK, HTML::index)
    }
}

private fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div { +"Hello from Ktor" }
        div { id = "root" }
        script(src = "/static/react.js") {}
    }
}
