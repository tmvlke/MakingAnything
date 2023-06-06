package wskim.main_app.core.navigation.dto

data class LayoutDetailDTO(
    val screen : Screen
) {
    enum class Screen {
        ConstraintLayout,
        ListInfinityScrollPaging3,
        CoordinatorLayout,
        SlidingPopUp,

        None,
    }
}