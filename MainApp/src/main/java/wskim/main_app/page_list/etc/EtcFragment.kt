package wskim.main_app.page_list.etc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import wskim.main_app.common.BaseSupportFragment
import wskim.main_app.ui.theme.MakingAnythingTheme

@AndroidEntryPoint
class EtcFragment : BaseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                SetMyContentView()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SetMyContentView() {
        MakingAnythingTheme {
            Text(text = "EtcFragment")
        }
    }
}