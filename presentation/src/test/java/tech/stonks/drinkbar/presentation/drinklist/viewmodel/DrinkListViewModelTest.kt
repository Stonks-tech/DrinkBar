package tech.stonks.drinkbar.presentation.drinklist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.UseCaseExecutor
import tech.stonks.drinkbar.domain.drinklist.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.usecase.GetDrinkListUseCase
import tech.stonks.drinkbar.presentation.drinklist.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkPresentationModel

class DrinkListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var _getDrinkListUseCase: GetDrinkListUseCase
    private lateinit var _drinkDomainToPresentationMapper: DrinkDomainToPresentationMapper
    private lateinit var _useCaseExecutor: UseCaseExecutor
    private lateinit var _viewModel: DrinkListViewModel

    @Before
    fun setup() {
        _getDrinkListUseCase = mockk()
        _drinkDomainToPresentationMapper = mockk()
        _useCaseExecutor = mockk()
        _viewModel = DrinkListViewModel(
            _getDrinkListUseCase,
            _drinkDomainToPresentationMapper
        ) { _useCaseExecutor }
    }

    @Test
    fun `given nothing when idle then default state`() {
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }

        val observer = _viewModel.state.test()

        observer.assertValue(DrinkListState(emptyList(), false))
    }

    @Test
    fun `given nothing when onEntered then drinks are loaded`() {
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }

        val observer = _viewModel.state.test()
        _viewModel.onEntered()

        observer.assertValue(DrinkListState(listOf(DRINK_PRESENTATION), false))
    }

    @Test
    fun `given nothing when onEntered then loading state is published before list`() {
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }

        val observer = _viewModel.state.test()
        _viewModel.onEntered()

        observer.assertValueHistory(
            DrinkListState(emptyList(), false),
            DrinkListState(emptyList(), true),
            DrinkListState(listOf(DRINK_PRESENTATION), false)
        )
    }

    @Test
    fun `given nothing when onRefreshAction then drinks are loaded`() {
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }

        val observer = _viewModel.state.test()
        _viewModel.onRefreshAction()

        observer.assertValue(DrinkListState(listOf(DRINK_PRESENTATION), false))
    }

    @Test
    fun `given nothing when onRefreshAction then loading state is published before list`() {
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }

        val observer = _viewModel.state.test()
        _viewModel.onRefreshAction()

        observer.assertValueHistory(
            DrinkListState(emptyList(), false),
            DrinkListState(emptyList(), true),
            DrinkListState(listOf(DRINK_PRESENTATION), false)
        )
    }


    companion object {
        private val DRINK_DOMAIN = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail"
        )

        private val DRINK_PRESENTATION = DrinkPresentationModel(
            id = "1",
            name = "name",
            description = "description",
            thumbnail = "thumbnail"
        )
    }
}