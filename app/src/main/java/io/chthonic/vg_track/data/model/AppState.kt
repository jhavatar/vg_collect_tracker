package io.chthonic.vg_track.data.model

import com.yheriatovych.reductor.annotations.CombinedState

/**
 * Created by jhavatar on 2/26/2017.
 */
@CombinedState
interface AppState {
    val todoList: List<TodoItem>
}