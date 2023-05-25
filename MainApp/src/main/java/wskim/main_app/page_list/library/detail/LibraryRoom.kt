package wskim.main_app.page_list.library.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.mvvm.viewmodel.LibraryRoomViewModel
import wskim.main_app.ui.ActionBarTitleBackBtn

@Preview
@Composable
fun LibraryRoom(
    viewModel: LibraryRoomViewModel? = null,
    actions: MainActions? = null
) {

    Column {

        ActionBarTitleBackBtn(
            title = "Room",
            backBtnClick = {
                actions?.upPress?.invoke()
            }
        )

//        val pagingData: LazyPagingItems<String> = viewModel!!.pagingItems.collectAsLazyPagingItems()
//
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth().weight(1f),
//        ) {
//            items(
//                count = pagingData.itemCount,
//                key = pagingData.itemKey(),
//                contentType = pagingData.itemContentType()
//            ) { index ->
//                val item = pagingData[index]
//                // 아이템 표시
//                item?.let {
//                    Text(text = it, modifier = Modifier.fillMaxWidth().height(40.dp))
//                }
//            }
//
//            pagingData.apply {
//                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item { LoadingItem() }
//                    }
//                    loadState.append is LoadState.Loading -> {
//                        item { LoadingItem() }
//                    }
//                    loadState.refresh is LoadState.Error -> {
//                        val error = pagingData.loadState.refresh as LoadState.Error
//                        item { ErrorItem(error.error) }
//                    }
//                    loadState.append is LoadState.Error -> {
//                        val error = pagingData.loadState.append as LoadState.Error
//                        item { ErrorItem(error.error) }
//                    }
//                }
//            }
//
//        }
    }
}
