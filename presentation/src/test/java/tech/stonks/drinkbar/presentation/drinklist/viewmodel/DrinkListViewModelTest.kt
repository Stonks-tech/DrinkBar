@file:Suppress("UNCHECKED_CAST")

package tech.stonks.drinkbar.presentation.drinklist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import tech.stonks.drinkbar.domain.cleanarchitecture.exception.DomainException
import tech.stonks.drinkbar.domain.cleanarchitecture.exception.UnknownDomainException
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.UseCaseExecutor
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinklist.usecase.GetDrinkListUseCase
import tech.stonks.drinkbar.domain.drinklist.usecase.SearchDrinksUseCase
import tech.stonks.drinkbar.presentation.drink.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel
import tech.stonks.drinkbar.presentation.drinklist.model.DrinkListState

class DrinkListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var _getDrinkListUseCase: GetDrinkListUseCase
    private lateinit var _searchDrinksUseCase: SearchDrinksUseCase
    private lateinit var _drinkDomainToPresentationMapper: DrinkDomainToPresentationMapper
    private lateinit var _useCaseExecutor: UseCaseExecutor
    private lateinit var _viewModel: DrinkListViewModel

    @Before
    fun setup() {
        _getDrinkListUseCase = mockk()
        _searchDrinksUseCase = mockk()
        _drinkDomainToPresentationMapper = mockk()
        _useCaseExecutor = mockk()
        _viewModel = DrinkListViewModel(
            _getDrinkListUseCase,
            _searchDrinksUseCase,
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
    fun `given nothing when onEntered called then call getDrinkListUseCase`() {
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _searchDrinksUseCase.executeInBackground("") } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }
        every { _useCaseExecutor.execute(_searchDrinksUseCase, "", any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }

        _viewModel.onEntered()

        verify(exactly = 0) { _useCaseExecutor.execute(_searchDrinksUseCase, any(), any(), any()) }
        verify { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) }
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

    @Test
    fun `given query when onSearchAction called then call searchDrinksUseCase`() {
        val query = "Margarita"
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _searchDrinksUseCase.executeInBackground(query) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }
        every {
            _useCaseExecutor.executeDebounced(
                _searchDrinksUseCase,
                query,
                300,
                any(),
                any()
            )
        } answers {
            (args[3] as (List<DrinkDomainModel>) -> Unit).invoke(listOf(DRINK_DOMAIN))
        }

        _viewModel.onSearchAction(query)

        verify { _useCaseExecutor.executeDebounced(_searchDrinksUseCase, query, 300, any(), any()) }
        verify(exactly = 0) { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) }
    }

    @Test
    fun `given query when onSearchAction called then should publish loading before list`() {
        val query = "Margarita"
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _searchDrinksUseCase.executeInBackground(query) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }
        every {
            _useCaseExecutor.executeDebounced(
                _searchDrinksUseCase,
                query,
                300,
                any(),
                any()
            )
        } answers {
            (args[3] as ((List<DrinkDomainModel>) -> Unit)).invoke(listOf(DRINK_DOMAIN))
        }

        val observer = _viewModel.state.test()
        _viewModel.onSearchAction(query)

        observer.assertValueHistory(
            DrinkListState(emptyList(), false),
            DrinkListState(emptyList(), false, searchQuery = query),
            DrinkListState(emptyList(), true, searchQuery = query),
            DrinkListState(listOf(DRINK_PRESENTATION), false, searchQuery = query)
        )
    }

    @Test
    fun `given query when search failed then should publish loading false`() {
        val query = "Margarita"
        every { _getDrinkListUseCase.executeInBackground(Unit) } returns listOf(DRINK_DOMAIN)
        every { _searchDrinksUseCase.executeInBackground(query) } returns listOf(DRINK_DOMAIN)
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every { _useCaseExecutor.execute(_getDrinkListUseCase, Unit, any(), any()) } answers {
            thirdArg<(List<DrinkDomainModel>) -> Unit>().invoke(listOf(DRINK_DOMAIN))
        }
        every {
            _useCaseExecutor.executeDebounced(
                _searchDrinksUseCase,
                query,
                300,
                any(),
                any()
            )
        } answers {
            (lastArg<(DomainException) -> Unit>()).invoke(UnknownDomainException(Exception()))
        }

        val observer = _viewModel.state.test()
        _viewModel.onSearchAction(query)

        observer.assertValueHistory(
            DrinkListState(emptyList(), false),
            DrinkListState(emptyList(), false, searchQuery = query),
            DrinkListState(emptyList(), true, searchQuery = query),
            DrinkListState(emptyList(), false, searchQuery = query)
        )
    }


    companion object {
        private val DRINK_DOMAIN = DrinkDomainModel(
            id = "1",
            name = "name",
            description = "description",
            image = "image",
            thumbnail = "thumbnail",
            ingredients = emptyList(),
        )

        private val DRINK_PRESENTATION = DrinkPresentationModel(
            id = "1",
            name = "name",
            description = "description",
            thumbnail = "thumbnail",
            ingredients = emptyList(),
        )
    }
}