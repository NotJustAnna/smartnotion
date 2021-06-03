package net.notjustanna.smartnotion.client.react

import kotlinx.browser.document
import kotlinx.browser.window
import net.notjustanna.smartnotion.client.react.components.App
import react.StrictMode
import react.child
import react.dom.render

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            StrictMode {
                child(App)
            }
        }
    }
}
