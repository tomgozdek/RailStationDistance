package com.tomgozdek.railstationdistance.distance

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomgozdek.railstationdistance.getOrAwaitValue
import com.tomgozdek.railstationdistance.repository.StationsRepository
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
class DistanceViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var mockRepository : StationsRepository
    private lateinit var viewModel : DistanceViewModel

    @Before
    fun setup(){
        mockRepository = Mockito.mock(StationsRepository::class.java)
        viewModel = DistanceViewModel(mockRepository)
    }

    @Test
    fun onStartStationSearchClick_onClick_onSearchStationRequestedIsNotNull(){
        val viewModel = DistanceViewModel(mockRepository)
        viewModel.onStartStationSearchClick()

        val searchRequestedValue = viewModel.onSearchStationRequested.getOrAwaitValue()
        assertThat(searchRequestedValue.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun onDestinationStationSearchClick_onClick_onSearchStationRequestedIsNotNull(){
        viewModel.onDestinationStationSearchClick()

        val searchRequestedValue = viewModel.onSearchStationRequested.getOrAwaitValue()
        assertThat(searchRequestedValue.getContentIfNotHandled(), not(nullValue()))
    }
}