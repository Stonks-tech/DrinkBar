package tech.stonks.drinkbar.di

interface ViewModelFactoryProvider<VMF> {
    fun provide(): VMF
}