package com.siwakorn.weatherforecast.domain.weatherforecast.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.siwakorn.weatherforecast.common.network.exception.ResponseDataErrorException
import com.siwakorn.weatherforecast.common.network.exception.ResponseErrorException
import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastRepository
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.ForecastResponse
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastBody
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetWeatherUseCaseImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var repository: WeatherForecastRepository

    private lateinit var useCase: GetWeatherUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        useCase = GetWeatherUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        clearAllMocks()
    }

    @Test
    fun `execute success`() = runBlocking {
        // mocking
        val request = GetWeatherBody("city", 0.0, 0.0, "unit")
        val response: WeatherResponse = mockk()

        coEvery { repository.getWeather(request) } returns flow {
            emit(response)
        }

        // test
        val flow = repository.getWeather(request)

        // verify
        flow.collect { result ->
            Truth.assertThat(result).isEqualTo(response)
        }
    }

    @Test
    fun `execute fail throw some response Exception`() = runBlocking {
        // mocking
        val request = GetWeatherBody("city", 0.0, 0.0, "unit")
        val messageException = "exception"
        val responseCode = 404
        val response = ResponseDataErrorException(messageException, responseCode)

        every { repository.getWeather(request) } returns flow {
            throw response
        }

        // test
        val flow = repository.getWeather(request)

        // verify
        flow.catch { exception ->
            Truth.assertThat(exception).isEqualTo(response)
            Truth.assertThat((exception as ResponseDataErrorException).responseErrorCode)
                .isEqualTo(response.responseErrorCode)
            Truth.assertThat((exception as ResponseDataErrorException).message)
                .isEqualTo(response.message)
        }.collect {}
    }

}