package wskim.main_app.ui.common

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

@Composable
fun FragmentPage(fragment: Fragment) {
    AndroidView(
        factory = { context ->
            FrameLayout(context).apply {
                id = ViewCompat.generateViewId()
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { view ->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(view.id, fragment)
            transaction.commit()
        }
    )
}