package lobna.swvl.adecadeofmovies.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import lobna.swvl.adecadeofmovies.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(activityDetailsBinding.root)

        activityDetailsBinding.dvm = detailsViewModel

        detailsViewModel.onBackEvent.observe(this, { onBackPressed() })

        detailsViewModel.init(intent.getBundleExtra("data"))
    }
}