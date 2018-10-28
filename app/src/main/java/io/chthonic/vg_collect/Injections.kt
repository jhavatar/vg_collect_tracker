package io.chthonic.vg_collect

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.chthonic.vg_collect.business.service.StateService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by jhavatar on 4/2/2018.
 */
fun depInject(app: Application): Kodein {
    return Kodein {
        bind<Application>() with instance(app)
        bind<Context>() with instance(app.applicationContext)
        bind<Resources>() with instance(app.applicationContext.resources)
        bind<SharedPreferences>() with singleton {
            app.applicationContext.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        }
        bind<StateService>() with singleton{ StateService() }
        bind<Moshi>() with singleton {
            Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
        }
    }
}