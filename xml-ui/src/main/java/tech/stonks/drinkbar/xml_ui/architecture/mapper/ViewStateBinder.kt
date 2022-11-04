package tech.stonks.drinkbar.xml_ui.architecture.mapper

import tech.stonks.drinkbar.xml_ui.architecture.view.ViewsProvider

interface ViewStateBinder<in STATE:Any, in VIEWS_PROVIDER: ViewsProvider> {
    fun VIEWS_PROVIDER.bindState(state: STATE)
}