package net.notjustanna.smartnotion.client.react.components

import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.attrs
import react.functionalComponent
import styled.styledButton
import styled.styledDiv

external interface AppThemeSelectorProps : RProps {
    var setTheme: (AppTheme) -> Unit
}

val AppThemeSelector = functionalComponent<AppThemeSelectorProps> { props ->
    styledDiv {
        styledButton {
            +"Light Theme"
            attrs {
                onClickFunction = {
                    props.setTheme(AppTheme.LIGHT)
                }
            }
        }
        styledButton {
            +"Dark Theme"
            attrs {
                onClickFunction = {
                    props.setTheme(AppTheme.DARK)
                }
            }
        }
    }
}
