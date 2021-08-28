package lobna.swvl.adecadeofmovies.ui.home

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setupViewModel() {
        mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getYearsAdapter() {
        val yearsAdapter = mainViewModel.yearsAdapter
        assertThat(yearsAdapter, not(nullValue()))
    }
}