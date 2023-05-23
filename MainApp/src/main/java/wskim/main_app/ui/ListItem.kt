package wskim.main_app.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview
@Composable
fun LI_OnlyTextOfNumbering(
    index: Int = 0,
    text: String = "텍스트",
    itemClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.height(50.dp),
    ) {
        Text(
            text = "${index}. $text",
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures (
                        onTap = {
                            itemClick.invoke()
                        },
                    )
                }
                .fillMaxWidth(),
            fontSize = 15.sp,
        )
    }
}

@Preview
@Composable
fun LI_TextOfNumberingAndViewCount(
    index: Int = 0,
    text: String = "텍스트",
    viewCount: Int = 0,
    itemClick: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier.height(50.dp)
            .pointerInput(Unit) {
                detectTapGestures (
                    onTap = {
                        itemClick.invoke()
                    },
                )
            }.fillMaxWidth()
    ) {
        val (text1, text2) = createRefs()

        Text(
            text = "${index}. $text",
            modifier = Modifier
                .constrainAs(text1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            fontSize = 15.sp,
        )

        Text(
            text = "${viewCount}회",
            modifier = Modifier
                .constrainAs(text2) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            fontSize = 15.sp,
        )
    }
}