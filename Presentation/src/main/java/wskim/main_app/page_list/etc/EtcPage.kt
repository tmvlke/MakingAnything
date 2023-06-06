package wskim.main_app.page_list.etc

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import wskim.main_app.R
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.core.navigation.PopupList
import wskim.main_app.ui.theme.AppColor

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun EtcPage(
    actions: MainActions? = null
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
        ) {
            val (idTitle, idToDoList) = createRefs()

            createVerticalChain(
                elements = arrayOf(idTitle, idToDoList),
                chainStyle = ChainStyle.Packed
            )

            Text(
                modifier = Modifier.constrainAs(idTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(idToDoList.top)
                },
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                fontWeight = FontWeight.Black,
                text = stringResource(id = R.string.etcTitle),
            )

            Button(
                modifier = Modifier.constrainAs(idToDoList) {
                        top.linkTo(idTitle.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColor,
                    contentColor = Color.Black
                ),
                onClick = { actions?.gotoPopup?.invoke(PopupList.EtcTodoList) }
            ) {
                Text(text = "To Do List")
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            items(EtcPageTechList.values()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = "${it.name}: "
                    )
                    Text(text = it.techName, modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(40.dp)
                            .padding(end = 10.dp)
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(it.link)
                                    )
                                )
                            },
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "검색")
                    }
                }

                Divider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview
@Composable
fun EtcPopupScreen(onDismiss: () -> Unit = {}) {
    // 팝업 화면 구성 요소 작성
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false
        // ...
        )
    ) {
        // Dialog content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 100.dp),
                text = "* room 추가 후 레이아웃 탭에 적용 완료\n" +
                        "* 라이브러리 탭 내 room 페이지에 crud 및 라이브러리 탭 내에도 조회수 적용 예정\n" +
                        "* 각 메인탭 마다 검색 기능 추가 예정\n" +
                        "* navigation route 정리" // https://github.com/airbnb/lottie-android/blob/master/sample-compose/src/main/java/com/airbnb/lottie/sample/compose/Route.kt
            )
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.align(Alignment.BottomCenter).height(50.dp)
            ) {
                Text(text = "닫기")
            }
        }
    }
}

enum class EtcPageTechList(val techName: String, val link: String) {
    DisignPatteren_1("Clean architecture", "https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html"),
    DisignPatteren_2("Mvvm", "https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel"),
    UI_1("Compose", "https://developer.android.com/jetpack/compose?hl=ko"),
    UI_2("Navigation", "https://developer.android.com/jetpack/compose/navigation?hl=ko"),
    AsyncProcessing_1("Coroutines", "https://developer.android.com/kotlin/coroutines?hl=ko"),
    AsyncProcessing_2("Flow", "https://developer.android.com/kotlin/flow?hl=ko"),
    AsyncProcessing_3("WorkManager(예정)", "https://developer.android.com/jetpack/androidx/releases/work?hl=ko"),
    Network_1("Okhttp3", "https://square.github.io/okhttp"),
    Network_2("Retrofit2", "https://square.github.io/retrofit/"),
    DataSet("Paging3", "https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=ko"),
    Image("coil-compose", "https://coil-kt.github.io/coil/compose/"),
    DataBase("Room", "https://developer.android.com/training/data-storage/room?hl=ko"),
    Animation("lottie", "https://github.com/airbnb/lottie/blob/master/android-compose.md"),
    TDD("작성 예정", "https://developer.android.com/training/data-storage/room?hl=ko"),
    BDD("작성 예정", "https://developer.android.com/training/data-storage/room?hl=ko"),

}