package wskim.main_app.page_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.launch
import wskim.baselibrary.ui.MainTab
import wskim.corefeature.BaseLog
import wskim.main_app.navigation.MainActions
import wskim.main_app.page_list.component.ComponentPage
import wskim.main_app.page_list.etc.EtcPage
import wskim.main_app.page_list.layout.LayoutPage
import wskim.main_app.page_list.library.LibraryPage
import wskim.main_app.page_list.list.ListPage
import wskim.main_app.ui.theme.MakingAnythingTheme
import wskim.main_app.viewmodel.ComponentViewModel
import wskim.main_app.viewmodel.EtcViewModel
import wskim.main_app.viewmodel.LayoutViewModel
import wskim.main_app.viewmodel.LibraryViewModel
import wskim.main_app.viewmodel.ListViewModel
import wskim.main_app.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun MainPage(
    mainViewModel: MainViewModel = MainViewModel(null),
    layoutViewModel: LayoutViewModel = LayoutViewModel(null),
    componentViewModel: ComponentViewModel = ComponentViewModel(null),
    listViewModel: ListViewModel = ListViewModel(null),
    libraryViewModel: LibraryViewModel = LibraryViewModel(null),
    etcViewModel: EtcViewModel = EtcViewModel(null),
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
            Text(text = mainViewModel.text.value)
            Text(text = "클릭하시오", modifier = Modifier.clickable { mainViewModel.onClick() })

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
                        MainTab.Layout.index -> LayoutPage(layoutViewModel, actions)
                        MainTab.Component.index -> ComponentPage(componentViewModel, actions)
                        MainTab.List.index -> ListPage(listViewModel, actions)
                        MainTab.Library.index -> LibraryPage(libraryViewModel, actions)
                        MainTab.Etc.index -> EtcPage(etcViewModel, actions)
                    }
                }

                Divider(color = Color.Black, thickness = 1.dp)

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
                                    Text(text = mainTab.title)
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
            mainViewModel.textChange()
            BaseLog.d("asdasdasd", "sssssss")
        }
    }
}