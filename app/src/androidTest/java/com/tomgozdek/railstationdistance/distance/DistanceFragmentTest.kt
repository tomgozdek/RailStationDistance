package com.tomgozdek.railstationdistance.distance

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tomgozdek.railstationdistance.R
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DistanceFragmentTest {

    lateinit var navController: NavController
    private lateinit var distanceScenario : FragmentScenario<DistanceFragment>

    @Before
    fun setup(){
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)

        distanceScenario = launchFragmentInContainer<DistanceFragment>()
        distanceScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun test_onStartStationContainerCLick_NavigatesToSearchFragment(){
        onView(ViewMatchers.withId(R.id.startStationContainer)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id,`is`(R.id.searchFragment))
    }

    @Test
    fun test_onDestinationStationContainerCLick_NavigatesToSearchFragment(){
        onView(ViewMatchers.withId(R.id.startStationContainer)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id,`is`(R.id.searchFragment))
    }
}