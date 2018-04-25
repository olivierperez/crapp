package fr.o80.featurereminder.usecase

import fr.o80.crapp.data.TimesheetRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

/**
 * @author Olivier Perez
 */
@ExtendWith(value = [DaggerMockExtension::class])
internal class TotalPerDayTest {

    @Mock
    lateinit var timesheetRepository: TimesheetRepository

    @InjectMocks
    lateinit var totalPerDay: TotalPerDay

    @Test
    @DisplayName("Super test JUnit5")
    fun test() {
        totalPerDay.today().subscribe()
        assertEquals(1, 1)
    }
}
