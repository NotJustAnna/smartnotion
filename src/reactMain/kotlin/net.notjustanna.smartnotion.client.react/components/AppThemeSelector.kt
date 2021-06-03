package net.notjustanna.smartnotion.client.react.components

import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.attrs
import react.functionalComponent
import styled.css
import styled.styledButton
import styled.styledDiv

external interface AppThemeSelectorProps : RProps {
    var setTheme: (AppTheme) -> Unit
}

val AppThemeSelector = functionalComponent<AppThemeSelectorProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            padding = "40px"
            background = "rgb(152,154,155,1)"
            background = "linear-gradient(90deg, rgba(255,255,255,1) 40%, rgba(47,52,55,1) 60%)"
            height = 100.vh
        }
        styledDiv {
            css {
                flex(flexBasis = 50.pct)
                display = Display.flex
                alignItems = Align.center
                padding = "40px"
                justifyContent = JustifyContent.center
            }
            styledButton {
                attrs {
                    onClickFunction = {
                        props.setTheme(AppTheme.LIGHT)
                    }
                }
                +"Light Theme"
            }
        }
        styledDiv {
            css {
                flex(flexBasis = 50.pct)
                display = Display.flex
                alignItems = Align.center
                padding = "40px"
                justifyContent = JustifyContent.center
            }
            styledButton {
                attrs {
                    onClickFunction = {
                        props.setTheme(AppTheme.DARK)
                    }
                }
                +"Dark Theme"
            }
        }
    }
}
