package io.chthonic.vg_track.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import io.chthonic.mythos.mvp.MVPDispatcher
import io.chthonic.mythos.mvp.PresenterCacheLoaderCallback
import io.chthonic.vg_track.R
import io.chthonic.vg_track.ui.presenter.MainPresenter
import io.chthonic.vg_track.ui.vu.MainVu
import timber.log.Timber


class MainActivity : DrawerMVPActivity<MainPresenter, MainVu>() {

    companion object {
        private val MVP_UID by lazy {
            MainActivity::class.hashCode()
        }
    }

    override fun createMVPDispatcher(): MVPDispatcher<MainPresenter, MainVu> {

        return MVPDispatcher(MVP_UID,
                PresenterCacheLoaderCallback(this, { MainPresenter() }),
                ::MainVu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        signIn()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        Timber.d("onOptionsItemSelected, id = $id")
        if (id == R.id.action_settings) {
            return true

        }
        return super.onOptionsItemSelected(item)
    }
}
