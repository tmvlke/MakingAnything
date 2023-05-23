package wskim.main_app.page_list.library

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wskim.main_app.navigation.MainActions
import wskim.main_app.navigation.dto.ComponentDetailDTO
import wskim.main_app.ui.LI_OnlyTextOfNumbering
import wskim.main_app.viewmodel.ComponentViewModel
import wskim.main_app.viewmodel.LibraryViewModel

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun LibraryPage(
    viewModel: LibraryViewModel = LibraryViewModel(null),
    actions: MainActions? = null
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp, 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {

//            items(
//                viewmodel.componentList.value
//            ) {
//                Text(text = it)
//            }

        itemsIndexed(
            viewModel.componentList
        ) { index, item ->
            LI_OnlyTextOfNumbering(
                index = index,
                text = item,
                itemClick = {
                    viewModel.itemClick(index)
                    actions?.gotoComponentDetail?.invoke(
                        ComponentDetailDTO(
                            id = System.currentTimeMillis().toInt(),
                            name = "됨?"
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryDetailPage(
    viewModel: ComponentViewModel = ComponentViewModel(null),
    actions: MainActions? = null
) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        LI_OnlyTextOfNumbering(
            index = viewModel.id.value,
            text = viewModel.name.value,
//        itemClick = {
//            viewModel.itemClick(index)
//            actions?.gotoComponentDetail?.invoke("1")
//        }
        )
    }
}