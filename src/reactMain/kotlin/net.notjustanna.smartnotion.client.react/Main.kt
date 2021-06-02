package net.notjustanna.smartnotion.client.react

import kotlinx.browser.document
import kotlinx.browser.window
import react.StrictMode
import react.child
import react.dom.render
import welcome

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            StrictMode {
                child(welcome) {
                    attrs {
                        name = "Kotlin/JS"
                    }
                }
            }
        }
    }
}
