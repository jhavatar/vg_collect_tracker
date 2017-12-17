package io.chthonic.vg_track.ui.vu

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import io.chthonic.mythos.mvp.FragmentWrapper
import io.chthonic.mythos.mvp.Vu
import io.chthonic.vg_track.R
import io.chthonic.vg_track.ui.activity.BaseActivity
import timber.log.Timber

/**
 * Created by jhavatar on 3/7/17.
 */
abstract class BaseVu(layoutInflater: LayoutInflater, activity: Activity, fragmentWrapper: FragmentWrapper?, parentView: ViewGroup?) :
        Vu(layoutInflater, activity, fragmentWrapper, parentView) {

    val baseActivity: BaseActivity
        get() = activity as BaseActivity


    private val loadingIndicator: PopupWindow by lazy {
        val inflator = LayoutInflater.from(activity)
        val loadingView = inflator.inflate(R.layout.layout_loading, null)
        val dimen = activity.resources.getDimensionPixelSize(R.dimen.loading_widget_dimen)
        val popup = PopupWindow(dimen, dimen)
        popup.contentView = loadingView
        popup
    }

    fun showLoading() {
        try {
            if (!loadingIndicator.isShowing) {
                loadingIndicator.showAtLocation(rootView, Gravity.CENTER, 0, 0)
            }

        } catch (t: Throwable) {
            Timber.w(t, "showLoading failed.")
        }
    }

    fun hideLoading() {
        try {
            if (loadingIndicator.isShowing) {
                loadingIndicator.dismiss()
            }

        } catch (t: Throwable) {
            Timber.w(t, "hideLoading failed.")
        }
    }

    override fun onDestroy() {
        hideLoading()
        super.onDestroy()
    }
}