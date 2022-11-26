package tech.stonks.drinkbar.di.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.EntryPoints

//https://stackoverflow.com/a/73007239/5441957
@Composable
inline fun <reified T> hiltEntryPoint(): T {
    return EntryPoints.get(LocalContext.current.applicationContext, T::class.java)
}