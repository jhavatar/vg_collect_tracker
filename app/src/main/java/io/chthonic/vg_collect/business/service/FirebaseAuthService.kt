package io.chthonic.vg_collect.business.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.yheriatovych.reductor.Actions
import io.chthonic.vg_collect.data.model.AppState
import io.chthonic.vg_collect.data.model.Auth
import io.chthonic.vg_collect.business.actions.AuthActions
import io.chthonic.vg_collect.business.observer.AppStateChangePublisher
import io.chthonic.vg_collect.ui.model.User
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by jhavatar on 12/19/2017.
 */
class FirebaseAuthService(private val stateService: StateService) {

    private val authActions: AuthActions by lazy {
        Actions.from(AuthActions::class.java)
    }

    val authState: Auth
        get() = stateService.state.auth

    val authStateListener: FirebaseAuth.AuthStateListener by lazy {
        object:FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                Timber.d("authStateListener: firebaseAuth = $authStateListener")
                update(firebaseAuth)
//                val firebaseUser = firebaseAuth.currentUser
//                Timber.d("authStateListener: firebaseAuth = $authStateListener, firebaseUser = $firebaseUser")
//                if ((firebaseUser != null) && !isSignedIn()) {
//                    signIn(firebaseUser)
//
//                } else if ((firebaseUser == null) && isSignedIn()) {
//                    signOut()
//                }
            }
        }
    }

    private val signedInChangePublisher = object: AppStateChangePublisher<Boolean>() {
        override fun shouldPublish(state: AppState, oldState: AppState?): Boolean {
            return hasObservers()
                    && ((oldState == null) || (oldState.auth.isSignedIn != state.auth.isSignedIn))
        }

        override fun getPublishInfo(state: AppState): Boolean {
            return state.auth.isSignedIn
        }
    }

    val signedInChangeObserver: Observable<Boolean>
        get() {
            return signedInChangePublisher.observable
        }

    init {
        stateService.addPublisher(signedInChangePublisher)
    }

    fun update(firebaseAuth: FirebaseAuth) {
        val firebaseUser = firebaseAuth.currentUser
        Timber.d("update: firebaseAuth = $authStateListener, firebaseUser = $firebaseUser")
        if ((firebaseUser != null) && !isSignedIn()) {
            signIn(firebaseUser)

        } else if ((firebaseUser == null) && isSignedIn()) {
            signOut()
        }
    }

    fun isSignedIn(): Boolean {
        return authState.isSignedIn
    }

    fun startSync() {
        update(FirebaseAuth.getInstance())
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener)
    }

    fun stopSync() {
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener)
    }

    private fun signIn(user: FirebaseUser) {
        Timber.d("signIn user $user")
        stateService.dispatch(authActions.signIn(
            User(user.uid,
                user.displayName ?: "",
                user.email ?: "",
                user.photoUrl?.toString() ?: "")
        ))
    }

    private fun signOut() {
        Timber.d("signOut")
        stateService.dispatch(authActions.signOut())
    }
}