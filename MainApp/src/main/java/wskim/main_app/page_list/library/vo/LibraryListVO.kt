package wskim.main_app.page_list.library.vo

import wskim.main_app.core.navigation.dto.LibraryDetailDTO

data class LibraryListVO (
    var index: Int = 0,
    val text: String,
    var screen: LibraryDetailDTO.Screen = LibraryDetailDTO.Screen.None,
    var viewCount: Int = 0
)