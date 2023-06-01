package wskim.main_app.page_list.layout.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.ui.ActionBarTitleBackBtn

@Preview
@Composable
fun LayoutSlidingPopup(
    actions: MainActions? = null
) {



    // 팝업 상태를 관리하는 MutableState
    val isPopupVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ActionBarTitleBackBtn(
            title = "SlidingPopup",
            backBtnClick = {
                actions?.upPress?.invoke()
            }
        )

        // 버튼을 클릭하면 팝업을 표시하거나 숨깁니다.
        Button(onClick = { isPopupVisible.value = !isPopupVisible.value }) {
            Text(text = if (isPopupVisible.value) "Hide Popup" else "Show Popup")
        }

        // 팝업이 표시되는지 여부에 따라 슬라이딩 팝업을 표시합니다.
        AnimatedVisibility(
            visible = isPopupVisible.value,
            enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight }) +
                    fadeIn(),
            exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight }) +
                    fadeOut()
        ) {
            // 팝업 컨텐츠
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Sliding Popup")
                    Text(text = "This is a sliding popup!")
                }
            }
        }
    }

}