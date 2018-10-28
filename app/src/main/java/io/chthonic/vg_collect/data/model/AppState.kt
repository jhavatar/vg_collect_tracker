package io.chthonic.vg_collect.data.model

import com.yheriatovych.reductor.annotations.CombinedState

/**
 * Created by jhavatar on 10/21/2018.
 */
@CombinedState
interface AppState {
    val fooState: FooState
}