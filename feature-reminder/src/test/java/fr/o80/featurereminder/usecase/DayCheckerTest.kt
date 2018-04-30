package fr.o80.featurereminder.usecase

import fr.o80.sample.lib.utils.mkcalendar
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks

/**
 * @author Olivier Perez
 */
internal class DayCheckerTest : BaseUnitTest() {

    @InjectMocks
    private lateinit var dayChecker: DayChecker

    @Test
    @DisplayName("Don't notify on weekend")
    fun dontNotifyOnWeekend() {
        val aSaturday = mkcalendar(2018, 5, 5)
        val aSunday = mkcalendar(2018, 5, 6)

        val notifyOnSaturday = dayChecker.shouldNotifyFor(aSaturday)
        val notifyOnSunday = dayChecker.shouldNotifyFor(aSunday)

        assertEquals(false, notifyOnSaturday)
        assertEquals(false, notifyOnSunday)
    }
}
