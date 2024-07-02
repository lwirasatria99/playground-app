package com.example.myplayground.feature.flow

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlowViewModelTest {

    private lateinit var viewModel: FlowViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        viewModel = FlowViewModel(testDispatchers)
    }

    @Test
    fun `countDownFlow, properly counts down from 5 to 0`() = runBlocking {
        viewModel.countDownFlow.test {
            for (i in 5 downTo 0) {
                testDispatchers.testDispatcher.scheduler.apply {
                    advanceTimeBy(1000L); runCurrent()
                }
                val emission = awaitItem()
                assertThat(emission).isEqualTo(i)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `square number test`() = runBlocking {
        val job = launch {
            viewModel.shareFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(9)
                cancelAndConsumeRemainingEvents()
            }
        }
        viewModel.squareNumber(3)
        job.join()
        job.cancel()
    }
}