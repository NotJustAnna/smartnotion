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
        meta(charset = "utf-8")
        meta(name = "viewport", content = "width=device-width, initial-scale=1")
        meta(name = "description", content = "SmartNotion")

        title("SmartNotion")
        style { unsafe { +"html,body { height: 100%; margin: 0 }" } }
    }
    body {
        noScript { +"You need to enable JavaScript to run this app." }
        div { id = "root" }
        script(src = "/static/react.js") {}
    }
}
