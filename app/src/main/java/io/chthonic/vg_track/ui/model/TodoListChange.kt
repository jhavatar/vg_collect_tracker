package io.chthonic.vg_track.ui.model

import io.chthonic.vg_track.data.model.TodoItem

/**
 * Created by jhavatar on 2/26/2017.
 */
data class TodoListChange(val item: TodoItem, val pos: Int)