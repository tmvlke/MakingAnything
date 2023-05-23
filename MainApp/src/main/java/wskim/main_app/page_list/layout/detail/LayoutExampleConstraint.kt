package wskim.main_app.page_list.layout.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview
@Composable
fun LayoutExampleConstraint() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (button1, button2, button3) = createRefs()

        Button(onClick = { /* Do something */ }, modifier = Modifier.constrainAs(button1) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text("Button 1")
        }

        Button(onClick = { /* Do something */ }, modifier = Modifier
            .width(200.dp)
            .constrainAs(button2) {
                top.linkTo(button1.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text("Button 2")
        }

        Button(onClick = { /* Do something */ }, modifier = Modifier.constrainAs(button3) {
            top.linkTo(button2.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.value(150.dp)
        }) {
            Text("Button 3")
        }
    }
}