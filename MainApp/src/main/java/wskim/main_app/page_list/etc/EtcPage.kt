package wskim.main_app.page_list.etc

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wskim.main_app.R
import wskim.main_app.core.navigation.MainActions

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                fontWeight = FontWeight.Black,
                text = stringResource(id = R.string.etcTitle)?:"asd",
            )
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
                        modifier = Modifier.width(80.dp).height(40.dp).padding(end = 10.dp)
                            .clickable {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
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
    TDD("작성 예정", "https://developer.android.com/training/data-storage/room?hl=ko"),
    BDD("작성 예정", "https://developer.android.com/training/data-storage/room?hl=ko"),

}