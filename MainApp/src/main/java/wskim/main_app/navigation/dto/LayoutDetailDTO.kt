package wskim.main_app.navigation.dto

data class LayoutDetailDTO(
    val screen : Screen
) {
    enum class Screen {
        ConstraintLayout,

        None,
    }
}