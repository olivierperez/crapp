package fr.o80.sample.sampleapplication;

import fr.o80.featureqrscan.feature.QRScan;
import fr.o80.sample.featuredashboard.feature.Dashboard;
import fr.o80.sample.lib.core.LibApplication;
import fr.o80.sample.lib.core.LibConfiguration;
import fr.o80.sample.timesheet.Timesheet;
import io.victoralbertos.rx2_permissions_result.RxPermissionsResult;

/**
 * @author Olivier Perez
 */
public class AppApplication extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RxPermissionsResult.register(this);
    }

    @Override
    protected LibConfiguration buildLibConfiguration() {
        return new LibConfiguration.Builder()
                .homeFeature(new Dashboard())
                .addFeature(new Timesheet())
                .addFeature(new QRScan())
                .build();
    }

}
