package wskim.main_app.page_list.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wskim.main_app.navigation.MainActions
import wskim.main_app.navigation.dto.LayoutDetailDTO
import wskim.main_app.ui.LI_TextOfNumberingAndViewCount
import wskim.main_app.viewmodel.LayoutViewModel

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun LayoutPage(
    viewModel: LayoutViewModel = LayoutViewModel(null),
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
            viewModel.layoutList
        ) { index, item ->
            LI_TextOfNumberingAndViewCount(
                index = index,
                text = item.text,
                viewCount = item.viewCount,
                itemClick = {
                    viewModel.itemClick(index)
                    actions?.gotoLayoutDetail?.invoke(
                        LayoutDetailDTO(
                            screen = try {
                                LayoutDetailDTO.Screen.values()[index]
                            } catch (e: IndexOutOfBoundsException) {
                                LayoutDetailDTO.Screen.None
                            }
                        )
                    )
                }
            )
        }
    }
}