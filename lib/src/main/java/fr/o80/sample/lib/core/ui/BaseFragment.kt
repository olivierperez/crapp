package fr.o80.sample.lib.core.ui

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.core.presenter.PresenterView

/**
 * Base Fragment for every

 * @author Olivier Perez
 */
abstract class BaseFragment : Fragment(), PresenterView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(layoutId, container, false)

        val p = presenter()
        if (p is Presenter && this is PresenterView) {
            p.attach(this)
        }

        return view
    }

    override fun onDestroyView() {
        presenter().dettach()

        super.onDestroyView()

        // Hide keyboard
        val view = view
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun presenter(): Presenter<out PresenterView>

}
