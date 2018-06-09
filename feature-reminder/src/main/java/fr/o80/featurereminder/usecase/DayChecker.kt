package fr.o80.featurereminder.usecase

import fr.o80.sample.lib.prefs.User
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class DayChecker
@Inject constructor(private val user: User) {
    infix fun shouldNotifyFor(today: Calendar): Boolean = today.get(Calendar.DAY_OF_WEEK) in user.reminderDays
}
