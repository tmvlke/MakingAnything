package wskim.main_app.page_list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wskim.main_app.common.BaseCompatActivity
import wskim.main_app.page_list.component.ComponentFragment
import wskim.main_app.page_list.etc.EtcFragment
import wskim.main_app.page_list.library.LibraryFragment
import wskim.main_app.page_list.list.ListFragment
import wskim.main_app.ui.common.FragmentPage
import wskim.main_app.ui.theme.MakingAnythingTheme

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
        MakingAnythingTheme {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                HorizontalPager(
                    pageCount = 10,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { page ->
                    // Our page content
                    when (page) {
                        0 -> FragmentPage(ComponentFragment())
                        1 -> FragmentPage(ListFragment())
                        2 -> FragmentPage(LibraryFragment())
                        3 -> FragmentPage(EtcFragment())
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text(text = "컴포넌트")
                    Text(text = "리스트")
                    Text(text = "라이브러리")
                    Text(text = "기타")
                }
            }

        }
    }

    override fun onBackButtonClickListener() {
        finish()
    }
}