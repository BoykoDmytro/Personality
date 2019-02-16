package bo.personality

import android.app.Application
import com.facebook.stetho.Stetho


/*
 * Created by Bo on 11.12.2018.
 */
class PersonalityApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}