package io.chthonic.vg_track.data.model

import com.yheriatovych.reductor.annotations.CombinedState
import io.chthonic.vg_track.ui.model.User

/**
 * Created by jhavatar on 2/26/2017.
 */
@CombinedState
interface AppState {
    val todoList: List<TodoItem>
    val auth: Auth
}