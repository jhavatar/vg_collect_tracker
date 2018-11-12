package io.chthonic.vg_collect.business.reducer

import com.yheriatovych.reductor.Reducer
import com.yheriatovych.reductor.annotations.AutoReducer
import io.chthonic.vg_collect.business.actions.AuthActions
import io.chthonic.vg_collect.data.model.Auth
import io.chthonic.vg_collect.ui.model.User
import timber.log.Timber

/**
 * Created by jhavatar on 3/1/17.
 */
@AutoReducer
abstract class AuthReducer: Reducer<Auth> {

    companion object {
        fun create(): AuthReducer {
            return AuthReducerImpl() //Note: usage of generated class
        }
    }

    @AutoReducer.InitialState
    internal fun initialState(): Auth {
        return Auth()
    }

    @AutoReducer.Action(
            value = AuthActions.SIGN_IN,
            from = AuthActions::class)
    fun signIn(auth: Auth, user: User): Auth  {
        Timber.d("signIn $user")
        return auth.copy(user = user)
    }

    @AutoReducer.Action(
            value = AuthActions.SIGN_OUT,
            from = AuthActions::class)
    fun signOut(auth: Auth): Auth  {
        Timber.d("signOut")
        return auth.copy(user = null)
    }

}

