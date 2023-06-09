package wskim.main_app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ActionBarTitleSearchBtn(
    title: String = "원하는 레이아웃을 찾아보세요",
    searchBtnClick: () -> Unit = {},
    inputText: MutableState<String> = mutableStateOf(""),
) {
    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 10.dp)
        ) {
            val (idTitle, idSearch) = createRefs()
            OutlinedTextField(
                value = inputText.value,
                modifier = Modifier
                    .constrainAs(idTitle) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(start = 10.dp)
                    .background(Color.White),
                onValueChange = { newText ->
                    inputText.value = newText
                },
//                label = { Text("Enter text") },
                placeholder = { Text(title) },
                // Set the text field keyboard options.
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            IconButton(
                modifier = Modifier.constrainAs(idSearch) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                onClick = { searchBtnClick.invoke() }
            ) {
                Icon(Icons.Default.Search, contentDescription = "검색")
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@Preview
@Composable
fun ActionBarTitleBackBtn(
    title: String = "원하는 레이아웃을 찾아보세요",
    backBtnClick: () -> Unit = {},
) {
    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 10.dp)
        ) {
            val (idSearch, idTitle) = createRefs()
            IconButton(
                modifier = Modifier.constrainAs(idSearch) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                onClick = { backBtnClick.invoke() }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "검색")
            }

            Text(
                modifier = Modifier
                    .constrainAs(idTitle) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(idSearch.end)
                    }
                    .padding(start = 10.dp),
                text = title,
                fontSize = 16.sp
            )
        }

        Divider(color = Color.Gray, thickness = 1.dp)
    }
}