package wskim.main_app.ui

import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import wskim.main_app.R

// https://coil-kt.github.io/coil/compose/

@Composable
@Preview
fun ImageViewerBannerOne(image: Any? = AppCompatResources.getDrawable(LocalContext.current, R.drawable.b1)) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.b2),
        contentDescription = stringResource(R.string.LbContentImage),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            //.clip(CircleShape)
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun ImageViewerBannerSlider(
    imageList: List<Any?> = arrayListOf(
        AppCompatResources.getDrawable(LocalContext.current, R.drawable.b1),
        AppCompatResources.getDrawable(LocalContext.current, R.drawable.b2)
    ),
    autoSlideDuration: Long = 5000L
) {
    val context = LocalContext.current
    val tabPagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2)

    LaunchedEffect(Unit) {
        while (true) {
            delay(autoSlideDuration)

            tabPagerState.animateScrollToPage(tabPagerState.currentPage + 1)
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (image, dots) = createRefs()

        HorizontalPager(
            pageCount = Int.MAX_VALUE,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            state = tabPagerState
        ) { pageIndex ->

            val imageIndex = (pageIndex + 1) % imageList.size

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageList[imageIndex])
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.b2),
                contentDescription = stringResource(R.string.LbContentImage),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                //.clip(CircleShape)
                    .pointerInput(Unit) {
                        detectTapGestures (
                            onTap = {
                                Toast.makeText(context, "${imageIndex+1}번째 배너 클릭", Toast.LENGTH_SHORT).show()
                            },
                        )
                    }
            )
        }

        DotsIndicator(
            dotsCount = imageList.size,
            currentPage = (tabPagerState.currentPage + 1) % imageList.size,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .constrainAs(dots) {
                    bottom.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}