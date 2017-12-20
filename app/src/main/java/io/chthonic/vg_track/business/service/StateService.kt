package io.chthonic.vg_track.business.service

import io.chthonic.vg_track.business.reducer.AuthReducer
import io.chthonic.vg_track.business.reducer.TodoListReducer
import io.chthonic.vg_track.data.client.StateClient
import io.chthonic.vg_track.data.model.AppState
import io.chthonic.vg_track.data.model.AppStateReducer
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by jhavatar on 3/1/17.
 */
class StateService {

    private val stateClient: StateClient<AppState> by lazy {
        val appStateReducer: AppStateReducer = AppStateReducer.builder()
                .getTodoListReducer(TodoListReducer.create())
                .getAuthReducer(AuthReducer.create())
                .build()
        StateClient<AppState>(appStateReducer)
    }

    val state: AppState
        get() {
            return stateClient.state
        }

    fun addPublisher(publisher: AppStateChangePublisher<*>) {
        stateClient.addPublisher(publisher)
    }

    fun removePublisher(publisher: AppStateChangePublisher<*>) {
        stateClient.removePublisher(publisher)
    }

    fun addPersister(persister: AppStateChangePersister<*>) {
        stateClient.addPersister(persister)
    }

    fun removePersister(persister: AppStateChangePersister<*>) {
        stateClient.removePersister(persister)
    }

    fun dispatch(action: Any) {
        stateClient.dispatch(action)
    }

    abstract class AppStateChangePublisher<I> : StateClient.StateChangePublisher<AppState> {
        protected val publisher: PublishSubject<I> by lazy {
            PublishSubject.create<I>()
        }

        val observable: Observable<I>
            get() {
                return publisher.hide()
            }

        fun hasObservers(): Boolean {
            return publisher.hasObservers()
        }

        abstract fun getPublishInfo(state: AppState): I

        override fun publish(state: AppState) {
            publisher.onNext(getPublishInfo(state))
        }
    }


    abstract class AppStateChangePersister<P> : StateClient.StateChangePersister<AppState>

}