package lobna.swvl.adecadeofmovies.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import lobna.swvl.adecadeofmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val moviesViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.mvm = moviesViewModel
    }
}