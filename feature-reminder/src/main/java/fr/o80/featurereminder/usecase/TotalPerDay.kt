package fr.o80.featurereminder.usecase

import fr.o80.crapp.data.TimesheetRepository
import fr.o80.sample.lib.dagger.FeatureScope
import javax.inject.Inject

@FeatureScope
class TotalPerDay
@Inject constructor(private val timesheetRepository: TimesheetRepository) {
    fun today(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}