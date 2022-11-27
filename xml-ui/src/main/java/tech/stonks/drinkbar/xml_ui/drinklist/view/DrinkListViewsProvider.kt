package tech.stonks.drinkbar.xml_ui.drinklist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tech.stonks.drinkbar.xml_ui.architecture.view.ViewsProvider

interface DrinkListViewsProvider : ViewsProvider {
    val drinksListView: RecyclerView
    val refreshButton: View
}