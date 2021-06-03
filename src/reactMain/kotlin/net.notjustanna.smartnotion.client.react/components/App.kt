package net.notjustanna.smartnotion.client.react.components

import react.*

enum class AppTheme {
    NOT_SET, LIGHT, DARK
}

val ThemeContext = createContext(AppTheme.LIGHT)

external interface AppProps : RProps {

}

val App = functionalComponent<AppProps> { props ->
    val (theme, setTheme) = useState(AppTheme.NOT_SET)

    if (theme == AppTheme.NOT_SET) {
        child(AppThemeSelector) {
            attrs.setTheme = setTheme
        }
    } else {
        val (token, setToken) = useState<String>()
        ThemeContext.Provider {
            attrs.value = theme

            if (token == null) {

            }
        }
    }
}
