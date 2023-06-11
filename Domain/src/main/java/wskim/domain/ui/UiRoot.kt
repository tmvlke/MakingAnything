package wskim.domain.ui

sealed class UiRoot {
    enum class MainTab {
        Layout,
        Component,
        Library,
        Etc
    }
}

sealed class UiLayout : UiRoot() {
    companion object {
        const val name : String = "레이아웃"
    }
}
sealed class UiComponent : UiRoot() {
    companion object {
        const val name : String = "컴포넌트"
    }
}
sealed class UiLibrary : UiRoot() {
    companion object {
        const val name : String = "라이브러리"
    }
}
sealed class UiEtc : UiRoot() {
    companion object {
        const val name : String = "기타"
    }
}