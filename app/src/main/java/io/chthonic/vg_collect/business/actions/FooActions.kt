package io.chthonic.vg_collect.business.actions

import com.yheriatovych.reductor.Action
import com.yheriatovych.reductor.annotations.ActionCreator

/**
 * Created by jhavatar on 3/30/2018.
 */
@ActionCreator
interface FooActions {
    companion object {
        const val UPDATE_FOO: String = "UPDATE_FOO"
    }

    @ActionCreator.Action(UPDATE_FOO)
    fun updateFoo(foo: Boolean): Action
}