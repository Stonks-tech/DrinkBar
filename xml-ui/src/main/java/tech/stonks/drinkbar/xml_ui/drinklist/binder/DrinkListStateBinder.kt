package tech.stonks.drinkbar.xml_ui.drinklist.binder

import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import tech.stonks.drinkbar.xml_ui.architecture.mapper.ViewStateBinder
import tech.stonks.drinkbar.xml_ui.drinklist.adapter.DrinksAdapter
import tech.stonks.drinkbar.xml_ui.drinklist.mapper.DrinkPresentationToUiMapper
import tech.stonks.drinkbar.xml_ui.drinklist.view.DrinkListViewsProvider

class DrinkListStateBinder(
    private val _drinkPresentationToUiMapper: DrinkPresentationToUiMapper
): ViewStateBinder<DrinkListState, DrinkListViewsProvider> {
    private val _drinksAdapter by lazy {
        DrinksAdapter()
    }

    override fun DrinkListViewsProvider.bindState(state: DrinkListState) {
        if (drinksListView.adapter == null) {
            drinksListView.adapter = _drinksAdapter
        }
        _drinksAdapter.items = state.drinks.map(_drinkPresentationToUiMapper::map)
    }
}