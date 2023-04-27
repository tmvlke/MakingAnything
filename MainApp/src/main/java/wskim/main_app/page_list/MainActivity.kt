package wskim.main_app.page_list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import wskim.baselibrary.ui.MainTab
import wskim.main_app.common.BaseCompatActivity
import wskim.main_app.page_list.component.ComponentFragment
import wskim.main_app.page_list.etc.EtcFragment
import wskim.main_app.page_list.library.LibraryFragment
import wskim.main_app.page_list.list.ListFragment
import wskim.main_app.ui.common.FragmentPage
import wskim.main_app.ui.theme.MakingAnythingTheme

@AndroidEntryPoint
class MainActivity : BaseCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetMyContentView()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Preview(showBackground = true)
    @Composable
    fun SetMyContentView() {

        val tabPagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        val tabs = MainTab.values().toList()

        MakingAnythingTheme {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
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
                        MainTab.Component.index -> FragmentPage(ComponentFragment())
                        MainTab.List.index -> FragmentPage(ListFragment())
                        MainTab.Library.index -> FragmentPage(LibraryFragment())
                        MainTab.Etc.index -> FragmentPage(EtcFragment())
                    }
                }

                Divider(color = Color.Black, thickness = 1.dp)

                TabRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
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
    }

    override fun onBackButtonClickListener() {
        finish()
    }
}