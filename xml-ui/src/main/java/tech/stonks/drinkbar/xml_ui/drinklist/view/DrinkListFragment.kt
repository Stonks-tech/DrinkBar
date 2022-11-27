package tech.stonks.drinkbar.xml_ui.drinklist.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import tech.stonks.drinkbar.presentation.drinklist.viewmodel.DrinkListViewModel
import tech.stonks.drinkbar.xml_ui.R
import tech.stonks.drinkbar.xml_ui.architecture.mapper.ViewStateBinder
import tech.stonks.drinkbar.xml_ui.architecture.view.BaseFragment
import tech.stonks.drinkbar.xml_ui.architecture.view.ViewsProvider
import tech.stonks.drinkbar.xml_ui.drinklist.mapper.DrinkListDestinationToUiMapper
import tech.stonks.drinkbar.xml_ui.drinklist.mapper.DrinkListNotificationPresentationToUiMapper
import javax.inject.Inject

@AndroidEntryPoint
class DrinkListFragment : BaseFragment<DrinkListState, Unit>(), DrinkListViewsProvider {
    override val layoutResourceId: Int = R.layout.fragment_drink_list
    override val viewModel: DrinkListViewModel by viewModels()

    @Inject
    override lateinit var destinationMapper: DrinkListDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper: DrinkListNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder: ViewStateBinder<DrinkListState, ViewsProvider>
    override lateinit var drinksListView: RecyclerView
    override lateinit var refreshButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.onEntered()
        }
    }

    override fun View.bindViews() {
        drinksListView = findViewById(R.id.drink_list)
        refreshButton = findViewById(R.id.fab)
        drinksListView.layoutManager = LinearLayoutManager(requireContext())
        refreshButton.setOnClickListener {
            viewModel.onRefreshAction()
        }
    }

}