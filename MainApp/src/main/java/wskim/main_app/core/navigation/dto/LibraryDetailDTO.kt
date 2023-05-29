package wskim.main_app.core.navigation.dto

data class LibraryDetailDTO(
    val screen : Screen
) {
    enum class Screen {
        Room,
        Lottie,

        None,
    }
}