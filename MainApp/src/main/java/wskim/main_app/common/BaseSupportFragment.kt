package wskim.main_app.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseSupportFragment : Fragment() {

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if(arguments != null){
            outState.putAll(arguments)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            arguments?.putAll(savedInstanceState)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(activity == null && isAdded) {
            onDestroy()
            return
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        // 프래그먼트가 정상적으로 연결되어 있다면 context 가 null 일수가 없음
        if(activity == null && isAdded) {
            onDestroy()
            return
        }
        super.onResume()
    }
}
