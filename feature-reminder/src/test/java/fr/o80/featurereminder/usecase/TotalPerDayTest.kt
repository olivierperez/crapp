package fr.o80.featurereminder.usecase

import com.nhaarman.mockito_kotlin.any
import fr.o80.crapp.data.TimesheetRepository
import fr.o80.crapp.data.entity.TimeEntry
import io.reactivex.Single
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock

/**
 * @author Olivier Perez
 */
internal class TotalPerDayTest : BaseUnitTest() {

    @Mock
    private lateinit var timesheetRepository: TimesheetRepository

    @InjectMocks
    private lateinit var totalPerDay: TotalPerDay

    @Test
    @DisplayName("Sum the hours of the day")
    fun test() {
        given(timesheetRepository.findByDateRange(any(), any()))
                .willReturn(Single.just(listOf(TimeEntry(hours = 5), TimeEntry(hours = 1))))

        val hours = totalPerDay.getTodayHours().blockingGet()

        assertEquals(6, hours)
    }

}
