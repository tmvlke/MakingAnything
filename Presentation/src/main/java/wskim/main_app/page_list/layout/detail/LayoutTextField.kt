package wskim.main_app.page_list.layout.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.ui.ActionBarTitleBackBtn
import wskim.main_app.viewmodel.LayoutTextFieldViewModel

@Preview
@Composable
fun LayoutTextField(
    viewModel: LayoutTextFieldViewModel? = null,
    actions: MainActions? = null
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ActionBarTitleBackBtn(
            title = "TextField 예제",
            backBtnClick = {
                actions?.upPress?.invoke()
            }
        )

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = viewModel?.inputTextValue?.value ?: "",
                onValueChange = {
                    viewModel?.resultTextValue?.value = it
                },
                modifier = Modifier.weight(1f)
            )

            // 버튼을 클릭하면 텍스트 필드에 입력한 값을 표시합니다.
            Button(
                onClick = { viewModel?.onButtonClick() }
            ) {
                Text(
                    text = "값 확인"
                )
            }
        }

        Text(
            text = viewModel?.resultTextValue?.value ?: "not found"
        )
    }
}