package wskim.main_app.page_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import wskim.baselibrary.ui.MainTab
import wskim.corefeature.BaseLog
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.mvvm.viewmodel.ComponentViewModel
import wskim.main_app.mvvm.viewmodel.LayoutViewModel
import wskim.main_app.mvvm.viewmodel.LibraryViewModel
import wskim.main_app.page_list.component.ComponentPage
import wskim.main_app.page_list.etc.EtcPage
import wskim.main_app.page_list.layout.LayoutPage
import wskim.main_app.page_list.library.LibraryPage
import wskim.main_app.ui.theme.MakingAnythingTheme

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun MainPage(
    layoutViewModel: LayoutViewModel = LayoutViewModel(null),
    componentViewModel: ComponentViewModel = ComponentViewModel(null),
    libraryViewModel: LibraryViewModel = LibraryViewModel(null),
    actions: MainActions? = null,
) {

    val tabPagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val tabs = MainTab.values().toList()

    MakingAnythingTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                HorizontalPager(
                    pageCount = tabs.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = tabPagerState
                ) { page ->
                    // Our page content
                    when (page) {
                        MainTab.Layout.ordinal -> LayoutPage(layoutViewModel, actions)
                        MainTab.Component.ordinal -> ComponentPage(componentViewModel, actions)
                        MainTab.Library.ordinal -> LibraryPage(libraryViewModel, actions)
                        MainTab.Etc.ordinal -> EtcPage(actions)
                    }
                }

                Divider(color = Color.Gray, thickness = 1.dp)

                TabRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    selectedTabIndex = tabPagerState.currentPage
                ) {
                    tabs.forEachIndexed { index, mainTab ->

                        Row {
                            Tab(
                                text = {
                                    Text(
                                        text = mainTab.title,
                                        fontSize = 14.sp,
                                        color = if (tabPagerState.currentPage == index) Color.Black else Color.Gray,
                                        fontWeight = if (tabPagerState.currentPage == index) FontWeight.Black else FontWeight.Normal
                                    )
                                },
                                selected = tabPagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        tabPagerState.scrollToPage(index)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        // 갱신된 UI를 보기 위해 LaunchedEffect를 사용
        LaunchedEffect(Unit) {
            BaseLog.d("MainPage", "refresh")
        }
    }
}