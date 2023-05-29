package wskim.main_app.page_list.library.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import wskim.main_app.R
import wskim.main_app.core.navigation.MainActions
import wskim.main_app.mvvm.viewmodel.LibraryRoomViewModel
import wskim.main_app.ui.ActionBarTitleBackBtn

@Preview
@Composable
fun LibraryLottie(
    viewModel: LibraryRoomViewModel? = null,
    actions: MainActions? = null
) {

    Column {

        ActionBarTitleBackBtn(
            title = "Lottie",
            backBtnClick = {
                actions?.upPress?.invoke()
            }
        )

        val scrollState = rememberScrollState()

        Column(Modifier.verticalScroll(scrollState)) {
            val composition1 = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
            val progress1 = animateLottieCompositionAsState(composition1.value)
            LottieAnimation(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .height(150.dp),
                composition = composition1.value,
                iterations = LottieConstants.IterateForever,
//            progress = { progress.progress },
            )

            Divider(Modifier.align(Alignment.CenterHorizontally).width(150.dp), color = Color.LightGray, thickness = 1.dp)

            val composition2 = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.square))
            val progress2 = animateLottieCompositionAsState(composition2.value)
            LottieAnimation(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth(),
                composition = composition2.value,
                iterations = LottieConstants.IterateForever,
//            progress = { progress.progress },
            )
        }
    }
}
