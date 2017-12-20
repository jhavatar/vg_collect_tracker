package io.chthonic.vg_track.ui.model

/**
 * Created by jhavatar on 3/1/17.
 */
data class User(
        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getToken() instead.
        val uuid: String,
        val name: String = "",
        val email: String = "",
        val photoUrl: String = ""

) {

    val username: String
        get() = email
}