package tech.stonks.drinkbar.presentation.drinkdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tech.stonks.drinkbar.domain.cleanarchitecture.exception.DomainException
import tech.stonks.drinkbar.domain.cleanarchitecture.exception.UnknownDomainException
import tech.stonks.drinkbar.domain.cleanarchitecture.usecase.UseCaseExecutor
import tech.stonks.drinkbar.domain.drink.model.DrinkDomainModel
import tech.stonks.drinkbar.domain.drinkdetails.usecase.GetDrinkDetailsUseCase
import tech.stonks.drinkbar.presentation.architecture.model.PresentationDestination
import tech.stonks.drinkbar.presentation.drink.mapper.DrinkDomainToPresentationMapper
import tech.stonks.drinkbar.presentation.drink.model.DrinkPresentationModel
import tech.stonks.drinkbar.presentation.drinkdetails.model.DrinkDetailsState

class DrinkDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var _getDrinkDetailsUseCase: GetDrinkDetailsUseCase
    private lateinit var _drinkDomainToPresentationMapper: DrinkDomainToPresentationMapper
    private lateinit var _useCaseExecutor: UseCaseExecutor
    private lateinit var _viewModel: DrinkDetailsViewModel

    @Before
    fun setup() {
        _getDrinkDetailsUseCase = mockk()
        _drinkDomainToPresentationMapper = mockk()
        _useCaseExecutor = mockk()
        _viewModel = DrinkDetailsViewModel(
            DRINK_ID,
            _getDrinkDetailsUseCase,
            _drinkDomainToPresentationMapper
        ) { _useCaseExecutor }
    }

    @Test
    fun `given nothing when initialize then default state`() {
        _viewModel.state.test()
            .assertValue(DrinkDetailsState(null, false))
    }

    @Test
    fun `given nothing when onEntered then drink details are loaded`() {
        every { _getDrinkDetailsUseCase.executeInBackground(DRINK_ID) } returns DRINK_DOMAIN
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every {
            _useCaseExecutor.execute(
                _getDrinkDetailsUseCase,
                DRINK_ID,
                any(),
                any()
            )
        } answers {
            thirdArg<(DrinkDomainModel) -> Unit>().invoke(DRINK_DOMAIN)
        }
        val observer = _viewModel.state.test()

        _viewModel.onEntered()

        observer.assertValue(DrinkDetailsState(DRINK_PRESENTATION, false))
    }

    @Test
    fun `given nothing when onEntered and getDrinkDetails fails then then isLoading is false`() {
        every { _getDrinkDetailsUseCase.executeInBackground(DRINK_ID) } returns DRINK_DOMAIN
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every {
            _useCaseExecutor.execute(
                _getDrinkDetailsUseCase,
                DRINK_ID,
                any(),
                any()
            )
        } answers {
            lastArg<(DomainException) -> Unit>().invoke(UnknownDomainException(Exception()))
        }
        val observer = _viewModel.state.test()

        _viewModel.onEntered()

        observer.assertValue(DrinkDetailsState(null, false))
    }

    @Test
    fun `given nothing when onRetryAction then drink details are loaded`() {
        every { _getDrinkDetailsUseCase.executeInBackground(DRINK_ID) } returns DRINK_DOMAIN
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every {
            _useCaseExecutor.execute(
                _getDrinkDetailsUseCase,
                DRINK_ID,
                any(),
                any()
            )
        } answers {
            thirdArg<(DrinkDomainModel) -> Unit>().invoke(DRINK_DOMAIN)
        }
        val observer = _viewModel.state.test()

        _viewModel.onRetryAction()

        observer.assertValue(DrinkDetailsState(DRINK_PRESENTATION, false))
    }

    @Test
    fun `given nothing when onRetryAction and getDrinkDetails fails then then isLoading is false`() {
        every { _getDrinkDetailsUseCase.executeInBackground(DRINK_ID) } returns DRINK_DOMAIN
        every { _drinkDomainToPresentationMapper.map(DRINK_DOMAIN) } returns DRINK_PRESENTATION
        every {
            _useCaseExecutor.execute(
                _getDrinkDetailsUseCase,
                DRINK_ID,
                any(),
                any()
            )
        } answers {
            lastArg<(DomainException) -> Unit>().invoke(UnknownDomainException(Exception()))
        }
        val observer = _viewModel.state.test()

        _viewModel.onRetryAction()

        observer.assertValue(DrinkDetailsState(null, false))
    }

    @Test
    fun `given nothing when onBackAction called then navigate back`() {
        val observer = _viewModel.destination.test()

        _viewModel.onBackAction()

        observer.assertValue { it is PresentationDestination.Back }
    }

    companion object {
        private const val DRINK_ID = "1"

        private val DRINK_DOMAIN = DrinkDomainModel(
            id = DRINK_ID,
            name = "Drink",
            description = "Description",
            image = "Image",
            thumbnail = "Thumbnail",
            ingredients = emptyList(),
        )
        private val DRINK_PRESENTATION = DrinkPresentationModel(
            id = DRINK_ID,
            name = "Drink",
            description = "Description",
            thumbnail = "Thumbnail",
            ingredients = emptyList(),
        )
    }
}