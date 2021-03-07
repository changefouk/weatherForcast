package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.siwakorn.weatherforecast.common.network.exception.ResponseErrorException
import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastRepository
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
class GetForecastUseCaseImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var repository: WeatherForecastRepository

    private lateinit var useCase: GetForecastUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testCoroutineDispatcher)
        useCase = GetForecastUseCaseImpl(repository)
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
        val request = GetForecastBody("city", "unit")
        val response: ForecastResponse = mockk()

        coEvery { repository.getForecast(request) } returns flow {
            emit(response)
        }

        // test
        val flow = repository.getForecast(request)

        // verify
        flow.collect { result ->
            Truth.assertThat(result).isEqualTo(response)
        }
    }

    @Test
    fun `execute fail throw some response Exception`() = runBlocking {
        // mocking
        val request = GetForecastBody("city", "unit")
        val messageException = "exception"
        val responseCode = 404
        val response = ResponseErrorException(messageException, responseCode)

        every { repository.getForecast(request) } returns flow {
            throw response
        }

        // test
        val flow = repository.getForecast(request)

        // verify
        flow.catch { exception ->
            Truth.assertThat(exception).isEqualTo(response)
            Truth.assertThat((exception as ResponseErrorException).responseErrorCode)
                .isEqualTo(response.responseErrorCode)
            Truth.assertThat((exception as ResponseErrorException).message)
                .isEqualTo(response.message)
        }.collect {}
    }

}