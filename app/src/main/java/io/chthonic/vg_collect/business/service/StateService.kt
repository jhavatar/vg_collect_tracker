package io.chthonic.vg_collect.business.service

import io.chthonic.vg_collect.business.observer.AppStateChangePublisher
import io.chthonic.vg_collect.business.observer.AppStateChangeSubject
import io.chthonic.vg_collect.business.reducer.AuthReducer
import io.chthonic.vg_collect.business.reducer.FooReducer
import io.chthonic.vg_collect.data.client.StateClient
import io.chthonic.vg_collect.data.model.AppState
import io.chthonic.vg_collect.data.model.AppStateReducer

/**
 * Created by jhavatar on 3/1/17.
 */
class StateService: AppStateChangeSubject {

    private val stateClient: StateClient<AppState> by lazy {
        val appStateReducer: AppStateReducer = AppStateReducer.builder()
            .getFooStateReducer(FooReducer.create())
            .getAuthReducer(AuthReducer.create())
            .build()
        StateClient<AppState>(appStateReducer)
    }

    val state: AppState
        get() {
            return stateClient.state
        }

    override fun addPublisher(publisher: AppStateChangePublisher<*>) {
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


    abstract class AppStateChangePersister<P> : StateClient.StateChangePersister<AppState>

}