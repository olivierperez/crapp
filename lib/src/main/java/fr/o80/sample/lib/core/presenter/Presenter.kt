package fr.o80.sample.lib.core.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Olivier Perez
 */
open class Presenter<out T : PresenterView> {

    var viewUnsafe: PresenterView? = null
        private set

    private val disposables = CompositeDisposable()

    fun attach(view: PresenterView?) {
        this.viewUnsafe = view
    }

    fun dettach() {
        disposables.clear()
        viewUnsafe = null
    }

    val view: T
        get() {
            when (viewUnsafe) {
                null -> throw RuntimeException("Trying to access view after it has been dettached.")
                else -> return viewUnsafe as T
            }
        }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
