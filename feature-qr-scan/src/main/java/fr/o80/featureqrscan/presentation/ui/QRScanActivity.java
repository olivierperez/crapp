package fr.o80.featureqrscan.presentation.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import io.reactivex.functions.Consumer;
import io.victoralbertos.rx2_permissions_result.Result;
import io.victoralbertos.rx2_permissions_result.RxPermissionsResult;

/**
 * This class starts the QR code scanning and handle the result.
 *
 * @author Olivier Perez
 */
public class QRScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = {Manifest.permission.CAMERA};

        RxPermissionsResult.on(this)
                .requestPermissions(permissions)
                .subscribe(new Consumer<Result<QRScanActivity>>() {
                               @Override
                               public void accept(Result<QRScanActivity> result) throws Exception {
                                   result.targetUI()
                                           .goScan(result.permissions(), result.grantResults());
                               }
                           }
                );
    }

    private void goScan(String[] permissions, int[] grantResults) {
        if (Manifest.permission.CAMERA.equals(permissions[0]) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            new IntentIntegrator(this).initiateScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("QRScanActivity", "Cancelled scan");
                finish();
            } else {
                Log.d("QRScanActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_SHORT).show();
                // TODO Do somthing cool
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
