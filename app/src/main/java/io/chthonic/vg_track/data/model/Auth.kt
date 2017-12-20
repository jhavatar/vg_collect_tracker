package io.chthonic.vg_track.data.model

import io.chthonic.vg_track.ui.model.User

/**
 * Created by jhavatar on 12/20/2017.
 */
data class Auth(val user: User? = null) {

    val isSignedIn: Boolean
        get() {
            return user != null
        }
}