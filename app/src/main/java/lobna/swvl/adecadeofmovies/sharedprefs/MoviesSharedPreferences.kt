package lobna.swvl.adecadeofmovies.sharedprefs

import android.content.Context

object MoviesSharedPreferences {

    const val prefsName = "MOVIES_PREFS"
    private const val installationAttribute = "FIRST_RUN"

    fun setInstalled(context: Context) {
        val settings = context.getSharedPreferences(prefsName, 0)
        val editor = settings.edit()
        editor.putBoolean(installationAttribute, false)
        editor.apply()
    }

    fun isRecentlyInstalled(context: Context): Boolean {
        var settings = context.getSharedPreferences(prefsName, 0)
        return settings.getBoolean(installationAttribute, true)
    }
}