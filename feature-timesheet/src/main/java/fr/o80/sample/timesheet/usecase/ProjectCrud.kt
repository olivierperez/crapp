package fr.o80.sample.timesheet.usecase

import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.Project
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class ProjectCrud @Inject
constructor() {

    fun create(project: Project): Single<Boolean>? {
        return project.save()
                .subscribeOn(Schedulers.io())
                .doOnSuccess {
                    Timber.d("Project saved %d %s", project.id, project.code)
                }
    }

}
