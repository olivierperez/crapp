package fr.o80.sample.lib.core.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Olivier Perez
 */
open class Presenter<T : PresenterView> {

    var viewUnsafe: T? = null
        private set

    private val disposables = CompositeDisposable()

    fun attach(view: T) {
        this.viewUnsafe = view
    }

    fun dettach() {
        disposables.dispose()
        viewUnsafe = null
    }

    fun view(): T {
        when (viewUnsafe) {
            null -> throw RuntimeException("Trying to access view after it has been dettached.")
            else -> return viewUnsafe as T
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
