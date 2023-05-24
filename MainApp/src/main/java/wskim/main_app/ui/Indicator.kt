package wskim.main_app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun DotsIndicator(
    modifier : Modifier = Modifier,
    dotsCount: Int = 2,
    currentPage: Int = 0
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(dotsCount) { index ->
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .padding(4.dp)
                    .background(
                        color = if (index == currentPage) Color.Black else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}