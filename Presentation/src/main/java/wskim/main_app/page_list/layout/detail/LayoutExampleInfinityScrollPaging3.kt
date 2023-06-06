package wskim.main_app.page_list.layout.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.ui.ActionBarTitleBackBtn
import wskim.main_app.viewmodel.LayoutExampleInfinityScrollViewModel

@Preview
@Composable
fun LayoutExampleInfinityScrollPaging3(
    viewModel: LayoutExampleInfinityScrollViewModel? = null,
    actions: MainActions? = null
) {

    Column {

        ActionBarTitleBackBtn(
            title = "리스트 무한 스크롤(paging3)",
            backBtnClick = {
                actions?.upPress?.invoke()
            }
        )

        val pagingData: LazyPagingItems<String> = viewModel!!.pagingItems.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
        ) {
            items(
                count = pagingData.itemCount,
                key = pagingData.itemKey(),
                contentType = pagingData.itemContentType(
                )
            ) { index ->
                val item = pagingData[index]
                // 아이템 표시
                item?.let {
                    Text(text = it, modifier = Modifier.fillMaxWidth().height(40.dp))
                }
            }

            pagingData.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val error = pagingData.loadState.refresh as LoadState.Error
                        item { ErrorItem(error.error) }
                    }
                    loadState.append is LoadState.Error -> {
                        val error = pagingData.loadState.append as LoadState.Error
                        item { ErrorItem(error.error) }
                    }
                }
            }

        }
    }
}

@Composable
fun LoadingItem() {
    // 로딩 아이템을 그리는 로직을 작성하세요.
    Text(text = "Loading...")
}

@Composable
fun ErrorItem(error: Throwable) {
    // 에러 아이템을 그리는 로직을 작성하세요.
    Text(text = "Error: ${error.message}")
}