package fr.o80.sample.timesheet.presentation.presenter;

import java.util.List;

import fr.o80.sample.lib.core.presenter.PresenterView;
import io.reactivex.Observable;

/**
 * @author Olivier Perez
 */
public interface TimesheetEntriesView extends PresenterView {
    Observable<Void> onInit();

    void showTimeEntries(List<Object> entries);
    void showError();
}
