package wskim.main_app

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@DisplayName("기본 ui 테스트")
class ExampleUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    @DisplayName("텍스트를 터치 했을때 텍스트가 변경되는지?")
    fun useAppContext() {
        val before = "클릭하시오"
        val after = "클릭됨"

        composeTestRule.setContent {
            val text = remember { mutableStateOf(before) }
            Text(text = text.value, modifier = Modifier.clickable {
                text.value = after
            })
        }

        composeTestRule.onNodeWithText(before).assertIsDisplayed()

        composeTestRule.onNodeWithText(before).performClick()

        Thread.sleep(1000L)

        composeTestRule.onNodeWithText(after).assertIsDisplayed()

        Thread.sleep(1000L)

    }
}