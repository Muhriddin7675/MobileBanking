import org.orbitmvi.orbit.ContainerHost

interface RegisterScreenContract {

    interface RegisterScreenModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface UiState {
        data class Display(val text: String = "") : UiState
        data class LanguageState(val bool:Boolean):UiState
    }

    sealed interface Intent {
        data class ShowToast(val msg: String) : Intent
        data class ClickNextButton(val phoneNumber: String) : Intent
        data class  SetLanguageState(val bool: Boolean) :Intent
        data object  SetUiLanguageState:Intent
        data object OpenLanguageDialog : Intent

    }

    sealed interface SideEffect {
        data class ShowToast(val msg: String) : SideEffect
        data class OpenLanguageDialog(val ruInUz:Boolean) : SideEffect

    }

}