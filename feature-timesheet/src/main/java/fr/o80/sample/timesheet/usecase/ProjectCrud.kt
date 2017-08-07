package fr.o80.sample.timesheet.usecase

import fr.o80.crapp.data.ProjectRepository
import fr.o80.crapp.data.entity.Project
import fr.o80.sample.lib.dagger.FeatureScope
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class ProjectCrud @Inject
constructor(private val projectRepository: ProjectRepository) {

    fun create(project: Project): Single<Boolean>? =
            project.save()
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess {
                        Timber.d("Project saved %d %s", project.id, project.code)
                    }

    fun all(): Single<List<Project>> =
            projectRepository.all()
                    .subscribeOn(Schedulers.io())

}
