package io.chthonic.vg_collect

import io.chthonic.vg_collect.utils.DebugUtils
import org.kodein.di.Kodein
import timber.log.Timber

/**
 * Created by jhavatar on 3/2/17.
 */
class App : android.app.Application() {
    companion object {
        lateinit var kodein: Kodein
            private set
    }

    override fun onCreate() {
        super.onCreate()

        kodein = depInject(this)

        if (DebugUtils.dontInitSinceAnalsing(this)) {
            return
        }
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else object: Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                // ignore
            }
        })
        DebugUtils.install(this)
    }
}