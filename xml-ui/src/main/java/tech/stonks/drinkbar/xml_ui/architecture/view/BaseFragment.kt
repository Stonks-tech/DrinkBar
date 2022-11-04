package tech.stonks.drinkbar.xml_ui.architecture.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination
import tech.stonks.drinkbar.presentation.architecture.viewmodel.BaseViewModel
import tech.stonks.drinkbar.xml_ui.architecture.mapper.NotificationPresentationToUiMapper
import tech.stonks.drinkbar.xml_ui.architecture.mapper.ViewStateBinder
import tech.stonks.drinkbar.xml_ui.navigation.mapper.DestinationPresentationToUiMapper

abstract class BaseFragment<STATE: Any, NOTIFICATION: Any>: Fragment(), ViewsProvider {

    companion object {
        private const val NO_LAYOUT_RESOURCE = 0
    }

    protected abstract val viewModel: BaseViewModel<STATE, NOTIFICATION>

    open val layoutResourceId: Int = NO_LAYOUT_RESOURCE

    abstract val destinationMapper: DestinationPresentationToUiMapper
    abstract val notificationMapper: NotificationPresentationToUiMapper<NOTIFICATION>
    abstract val viewStateBinder: ViewStateBinder<STATE, ViewsProvider>

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = if(layoutResourceId != NO_LAYOUT_RESOURCE) {
            inflater.inflate(layoutResourceId, container, false).apply {
                bindViews()
            }
        } else {
            null
        }
        observeViewModel()
        return view
    }

    abstract fun View.bindViews()

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::applyViewState)
        viewModel.notification.observe(viewLifecycleOwner, ::showNotification)
        viewModel.destination.observe(viewLifecycleOwner, ::navigateToDestination)
    }

    private fun applyViewState(state: STATE) {
        with(viewStateBinder) {
            bindState(state)
        }
    }

    private fun showNotification(notification: NOTIFICATION) {
        notificationMapper.toUi(notification).show()
    }

    private fun navigateToDestination(destination: PresentationDestination) {
        destinationMapper.toUi(destination).navigate()
    }
}