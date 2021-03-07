package com.siwakorn.weatherforecast.ui.weatherforecast.weather

import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.siwakorn.getOrAwaitValue
import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforecast.common.network.exception.ResponseDataErrorException
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.domain.weatherforecast.common.Coord
import com.siwakorn.weatherforecast.domain.weatherforecast.common.Weather
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherMain
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherUseCase
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.WeatherResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherSearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var resourceProvider: ResourceProvider

    @MockK
    lateinit var usecase: GetWeatherUseCase

    private lateinit var viewModel: WeatherSearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = spyk(WeatherSearchViewModel(resourceProvider, usecase))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        clearAllMocks()
    }

    @Test
    fun `getWeather success `() = runBlocking {
        // mocking
        val latitude = 100.0
        val longitude = 101.0
        val location: Location = mockk()
        val response: WeatherResponse = mockResponse()

        every { location.latitude } returns latitude
        every { location.longitude } returns longitude

        coEvery {
            usecase.execute(any())
        } returns flow {
            emit(response)
        }

        every {
            resourceProvider.string(
                R.string.config_weather_icon_url,
                response.weather.icon
            )
        } returns "icon"
        every {
            resourceProvider.string(
                R.string.temperature_celsius,
                response.weatherMain.temp
            )
        } returns "10"
        every {
            resourceProvider.string(
                R.string.humidity_percent,
                response.weatherMain.humidity
            )
        } returns "51"

        // test
        val loadingSlot = mutableListOf<Boolean>()
        val showContentSlot = mutableListOf<Unit>()
        viewModel.showContent.observeForever { showContentSlot.add(it) }
        viewModel.loading.observeForever { loadingSlot.add(it) }
        viewModel.getWeather(location = location)

        // verify
        assertThat(viewModel.weatherIconUrl.getOrAwaitValue()).isEqualTo("icon")
        assertThat(viewModel.temp.getOrAwaitValue()).isEqualTo("10")
        assertThat(viewModel.cityName.getOrAwaitValue()).isEqualTo(response.cityName)
        assertThat(viewModel.dateTime.getOrAwaitValue()).isEqualTo("Thursday, Jan 01 10:25")

        // count loading call
        verify { viewModel.showLoading() }
        verify { viewModel.hideLoading() }
        assertThat(loadingSlot[0]).isTrue()
        assertThat(loadingSlot[1]).isFalse()
        assertThat(loadingSlot.size).isEqualTo(2)

        // count show content call
        assertThat(showContentSlot.size).isEqualTo(1)
    }

    @Test
    fun `getWeather fail `() = runBlocking {
        // mocking
        val latitude = 100.0
        val longitude = 101.0
        val location: Location = mockk()
        val exception = ResponseDataErrorException("error", 400)

        every { location.latitude } returns latitude
        every { location.longitude } returns longitude

        coEvery {
            usecase.execute(any())
        } returns flow {
            throw exception
        }

        // test
        val loadingSlot = mutableListOf<Boolean>()
        val showContentSlot = mutableListOf<Unit>()
        viewModel.showContent.observeForever { showContentSlot.add(it) }
        viewModel.loading.observeForever { loadingSlot.add(it) }

        viewModel.getWeather(location = location)

        // verify
        assertThat(viewModel.weatherIconUrl.value).isNull()
        assertThat(viewModel.temp.value).isNull()
        assertThat(viewModel.cityName.value).isNull()
        assertThat(viewModel.dateTime.value).isNull()

        // count loading call
        verify { viewModel.showLoading() }
        verify { viewModel.hideLoading() }
        assertThat(loadingSlot[0]).isTrue()
        assertThat(loadingSlot[1]).isFalse()
        assertThat(loadingSlot.size).isEqualTo(2)

        // count show content call
        assertThat(showContentSlot.size).isEqualTo(0)

        verify { viewModel.showDialogError(exception) }
        assertThat(viewModel.dialogError.getOrAwaitValue()).isEqualTo(exception)
    }


    @Test
    fun `hasWeatherData == true`() = runBlockingTest {
        // mocking
        every { viewModel.hasWeatherData() } returns true

        // test
        val hasData = viewModel.hasWeatherData()

        assertThat(hasData).isTrue()
    }

    @Test
    fun `hasWeatherData == false`() = runBlockingTest {
        // mocking
        every { viewModel.hasWeatherData() } returns false

        // test
        val hasData = viewModel.hasWeatherData()

        assertThat(hasData).isFalse()
    }

    private fun mockResponse() = WeatherResponse(
        coord = Coord(latitude = 100.0, longitude = 101.0),
        weather = Weather(1, "main", "description", "icon"),
        weatherMain = WeatherMain(10.0, 11.0, 0.0, 20.0, 50, 51),
        cityName = "city",
        dateTime = 12345L,
        timeZone = 123456L
    )

}