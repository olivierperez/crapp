package fr.o80.featurereminder.usecase

import fr.o80.sample.lib.core.Const.NOTIFIABLE_DAYS
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class DayChecker
@Inject constructor() {
    infix fun shouldNotifyFor(today: Calendar): Boolean = today.get(Calendar.DAY_OF_WEEK) in NOTIFIABLE_DAYS
}
