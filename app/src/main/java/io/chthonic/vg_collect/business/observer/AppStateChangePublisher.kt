package io.chthonic.vg_collect.business.observer


import io.chthonic.vg_collect.data.client.StateClient
import io.chthonic.vg_collect.data.model.AppState
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by jhdevaal on 2018/03/24.
 */
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