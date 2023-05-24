package wskim.main_app.page_list.layout.vo

import wskim.main_app.core.navigation.dto.LayoutDetailDTO

data class LayoutListVO (
    var index: Int = 0,
    val text: String,
    var screen: LayoutDetailDTO.Screen = LayoutDetailDTO.Screen.None,
    var viewCount: Int = 0
)