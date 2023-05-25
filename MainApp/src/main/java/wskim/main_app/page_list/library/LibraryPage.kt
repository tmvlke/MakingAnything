package wskim.main_app.page_list.library

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.core.navigation.dto.LibraryDetailDTO
import wskim.main_app.mvvm.viewmodel.LibraryViewModel
import wskim.main_app.ui.ActionBarTitleSearchBtn
import wskim.main_app.ui.LI_TextOfNumberingAndViewCount

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun LibraryPage(
    viewModel: LibraryViewModel = LibraryViewModel(null),
    actions: MainActions? = null
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val inputText = remember { mutableStateOf("") }
        ActionBarTitleSearchBtn(inputText = inputText)

        LazyColumn(
//            contentPadding = PaddingValues(20.dp, 0.dp),
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {

            items(
                viewModel.libraryList
            ) { item ->
                LI_TextOfNumberingAndViewCount(
                    index = item.index,
                    text = item.text,
                    viewCount = item.viewCount,
                    itemClick = {
                        if(item.index == -1) {
                            Toast.makeText(context, "아직 고민 중인 레이아웃 입니다.", Toast.LENGTH_SHORT).show()
                            return@LI_TextOfNumberingAndViewCount
                        }

                        viewModel.itemClick(item.index)
                        actions?.gotoLibraryDetail?.invoke(LibraryDetailDTO(item.screen))
                    }
                )
            }
        }
    }

}