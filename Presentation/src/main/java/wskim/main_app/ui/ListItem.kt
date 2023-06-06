package wskim.main_app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

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
                    detectTapGestures(
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
    Box(
        modifier = Modifier
            .background(color = if (index == -1) Color.Gray else Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            itemClick.invoke()
                        },
                    )
                }
        ) {
            val (text1, text2) = createRefs()

            Text(
                text = "${if(index == -1) "-" else index}. $text",
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
}