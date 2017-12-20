package io.chthonic.vg_track.business.actions

import com.yheriatovych.reductor.Action
import com.yheriatovych.reductor.annotations.ActionCreator
import io.chthonic.vg_track.ui.model.User

/**
 * Created by jhavatar on 3/1/17.
 */
@ActionCreator
interface AuthActions {
    companion object {
        const val SIGN_IN: String = "SIGN_IN"
        const val SIGN_OUT: String = "SIGN_OUT"
    }

    @ActionCreator.Action(SIGN_IN)
    fun signIn(user: User): Action

    @ActionCreator.Action(SIGN_OUT)
    fun signOut(): Action


//    val Actions..from(AuthActions::class.java)
}