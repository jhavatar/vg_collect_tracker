package io.chthonic.vg_collect.data.model

import io.chthonic.vg_collect.ui.model.User

/**
 * Created by jhavatar on 12/20/2017.
 */
data class Auth(val user: User? = null) {

    val isSignedIn: Boolean
        get() {
            return user != null
        }
}