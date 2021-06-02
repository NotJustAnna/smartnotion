package net.notjustanna.smartnotion.client.react.components

import react.RProps
import react.functionalComponent
import react.useState

external interface AppProps : RProps {

}

enum class AppTheme {
    NOT_SET, LIGHT, DARK
}

val App = functionalComponent<AppProps> { props ->
    val (theme, setTheme) = useState<AppTheme>()

    if (theme == AppTheme.NOT_SET) {

    } else {

    }
}

data class AppState(
    val token: String?,
)
