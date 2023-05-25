package wskim.main_app.page_list.layout.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wskim.corefeature.BaseLog
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.mvvm.viewmodel.LayoutExampleInfinityScrollViewModel

@Preview
@Composable
fun LayoutCoordinatorLayout(
    viewModel: LayoutExampleInfinityScrollViewModel? = null,
    actions: MainActions? = null
) {

    val scrollState = rememberScrollState()
    val appBarHeight = 56.dp
    val expandedHeight = (56*4).dp
    val appBarOffset = remember { derivedStateOf { scrollState.value / expandedHeight.value * appBarHeight.value } }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = appBarHeight)
        ) {
            // 화면 내용
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(expandedHeight)
                    .background(Color.Blue)
            ) {
                // 헤더 콘텐츠
                Column{
                    Text(
                        text = "Header1",
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(appBarHeight)
                            .background(Color.Yellow)
                    )
                    Text(
                        text = "Header2",
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(appBarHeight)
                            .background(Color.Green)
                    )
                    Text(
                        text = "Header3",
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(appBarHeight)
                            .background(Color.Cyan)
                    )
                    Text(
                        text = "Header4",
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(appBarHeight)
                            .background(Color.Gray)
                    )
                }
            }

            repeat(100) {
                Text(
                    text = "Item $it",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .height(appBarHeight)
                .background(Color.Red)
        ) {
            // 액션바 콘텐츠
            Text(
                text = "ActionBar",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
                    .alpha(1f - (appBarOffset.value / appBarHeight.value))
            )

            BaseLog.d("scrollState.value", "${appBarHeight * 3} / ${scrollState.value.dp} / ${appBarOffset.value.dp}")

            val a = 200.dp < appBarOffset.value.dp
            val isVisible = remember { mutableStateOf(false) }
            isVisible.value = a

            if (isVisible.value) {
                Text(
                    text = "new Header1",
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.Red)
                )
            }
        }
    }

}