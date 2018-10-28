package io.chthonic.vg_collect.business.reducer

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.yheriatovych.reductor.Reducer
import com.yheriatovych.reductor.annotations.AutoReducer
import io.chthonic.vg_collect.App
import io.chthonic.vg_collect.business.actions.FooActions
import io.chthonic.vg_collect.data.model.FooState
import timber.log.Timber

/**
 * Created by jhavatar on 3/27/2018.
 */
@AutoReducer
abstract class FooReducer : Reducer<FooState> {

    companion object {
        fun create(): FooReducer {
            return FooReducerImpl() //Note: usage of generated class
        }
    }

//    val prefs: SharedPreferences by App.kodein.lazy.instance<SharedPreferences>()
//    val serializer: JsonAdapter<FooState> by App.kodein.lazy.instance<JsonAdapter<FooState>>()

    @AutoReducer.InitialState
    internal fun initialState(): FooState {
        return FooState()
    }


    @AutoReducer.Action(
            value = FooActions.UPDATE_FOO,
            from = FooActions::class)

    fun updateFoo(state: FooState, foo: Boolean): FooState {
        Timber.d("updateFoo $foo")
        return state.copy(foo = foo)
    }
}