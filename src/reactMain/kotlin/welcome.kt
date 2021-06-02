import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RProps
import react.RState
import react.dom.attrs
import react.dom.div
import react.dom.input
import react.functionalComponent
import react.useState

external interface WelcomeProps : RProps {
    var name: String
}

data class WelcomeState(val name: String) : RState

val welcome = functionalComponent<WelcomeProps> { props ->
    val (state, setState) = useState(WelcomeState(props.name))
    div {
        +"Hello, ${state.name}"
    }
    input {
        attrs {
            type = InputType.text
            value = state.name
            onChangeFunction = { event ->
                setState(
                    WelcomeState(name = (event.target as HTMLInputElement).value)
                )
            }
        }
    }
}
