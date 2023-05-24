package wskim.main_app.core.navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import wskim.main_app.core.common.BaseCompatActivity
import wskim.main_app.core.navigation.dto.ComponentDetailDTO
import wskim.main_app.core.navigation.dto.LayoutDetailDTO
import wskim.main_app.mvvm.viewmodel.ComponentViewModel
import wskim.main_app.mvvm.viewmodel.EtcViewModel
import wskim.main_app.mvvm.viewmodel.LayoutViewModel
import wskim.main_app.mvvm.viewmodel.LibraryViewModel
import wskim.main_app.page_list.MainPage
import wskim.main_app.page_list.component.ComponentDetailPage
import wskim.main_app.page_list.layout.detail.LayoutExampleConstraint
import wskim.main_app.page_list.layout.detail.LayoutExampleInfinityScrollPaging3

@AndroidEntryPoint
class MainActivity : BaseCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph()
        }
    }

    override fun onBackButtonClickListener() {
        finish()
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberAnimatedNavController()
    val actions = remember(navController) { MainActions(navController) }
    val context = LocalContext.current

    AnimatedNavHost(
        navController,
        startDestination = ScreenList.Home.name,
    ) {
        ///////////////////////////////////////////////////////
        // 메인 페이지
        ///////////////////////////////////////////////////////
        composable(
            route = ScreenList.Home.name,
        ) {
            val layoutViewModel: LayoutViewModel = viewModel(
                factory = HiltViewModelFactory(context, it)
            )

            val componentViewModel: ComponentViewModel = viewModel(
                factory = HiltViewModelFactory(context, it)
            )

            val libraryViewModel: LibraryViewModel = viewModel(
                factory = HiltViewModelFactory(context, it)
            )

            val etcViewModel: EtcViewModel = viewModel(
                factory = HiltViewModelFactory(context, it)
            )

            MainPage(
                layoutViewModel,
                componentViewModel,
                libraryViewModel,
                etcViewModel,
                actions
            )
        }

        ///////////////////////////////////////////////////////
        // 메인 페이지 -> 레이아웃 탭의 상세 페이지
        ///////////////////////////////////////////////////////
        val layoutDetail = ScreenList.LayoutDetail
        composable(
            "${layoutDetail.name}/{${layoutDetail.argName}}",
            arguments = listOf(navArgument(ScreenList.LayoutDetail.name) { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(700))
            },
        ) {
            val data = ScreenBundleUtils.parse<LayoutDetailDTO>(
                it.arguments?.getString(layoutDetail.argName)
                    ?: throw IllegalStateException("LayoutDetailDTO data null")
            )

            when(data.screen) {
                LayoutDetailDTO.Screen.None -> {
                    actions.upPress.invoke()
                }
                LayoutDetailDTO.Screen.ConstraintLayout -> LayoutExampleConstraint(actions)
                LayoutDetailDTO.Screen.ListInfinityScrollPaging3 -> LayoutExampleInfinityScrollPaging3(
                    hiltViewModel(it),
                    actions
                )
            }
        }

        ///////////////////////////////////////////////////////
        // 메인 페이지 -> 컴포넌트 탭 상세 페이지
        ///////////////////////////////////////////////////////
        val componentDetail = ScreenList.ComponentDetail
        composable(
            "${componentDetail.name}/{${componentDetail.argName}}",
            arguments = listOf(navArgument(ScreenList.ComponentDetail.name) { type = NavType.StringType }),
//            enterTransition = {
//                slideInVertically (
//                    initialOffsetY = { fullWidth -> fullWidth },
//                    animationSpec = tween(durationMillis = 500)
//                )
//            },
//            exitTransition = {
//                slideOutVertically(
//                    targetOffsetY = { fullWidth -> fullWidth },
//                    animationSpec = tween(durationMillis = 500)
//                )
//            }
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(700))
            },
//            popEnterTransition = {
//                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
//            },
//            popExitTransition = {
//                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
//            }
        ) {
            val viewModel = hiltViewModel<ComponentViewModel>(it)
            val data = ScreenBundleUtils.parse<ComponentDetailDTO>(
                it.arguments?.getString(componentDetail.argName)
                    ?: throw IllegalStateException("ComponentDetailDTO data null")
            )

            viewModel.inputId(data.id)
            viewModel.inputName(data.name)

            ComponentDetailPage(viewModel, actions)
        }
    }
}


class MainActions(navController: NavController) {

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoHome: () -> Unit = {
        navController.navigate(ScreenList.Home.name)
    }

    val gotoLayoutDetail: (LayoutDetailDTO) -> Unit = { dto ->
        navController.navigate(
            "${ScreenList.LayoutDetail.name}/${ScreenBundleUtils.build(dto)}"
        )
    }

    val gotoComponentDetail: (ComponentDetailDTO) -> Unit = { dto ->
        navController.navigate(
            "${ScreenList.ComponentDetail.name}/${ScreenBundleUtils.build(dto)}"
        )
    }
}