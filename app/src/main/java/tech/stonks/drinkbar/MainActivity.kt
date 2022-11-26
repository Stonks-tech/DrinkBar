package tech.stonks.drinkbar

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import tech.stonks.drinkbar.composeui.architecture.theme.AppTheme
import tech.stonks.drinkbar.navigation.DrinkBarNavHost

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                DrinkBarNavHost()
            }
        }
    }
}