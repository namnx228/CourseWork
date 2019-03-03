package com.example.namxuan.mylastadnroid;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.io.IOException;
import java.util.List;

public class BarCode extends AsyncTask<Uri,Integer, Boolean > {

    Boolean result;
    Context context;
    TextView txtBarcode;
    public BarCode(Context context, TextView txtBarcode) {
        super();
        this.context = context;
        this.txtBarcode = txtBarcode;
    }
    @Override
    protected Boolean doInBackground(Uri... uris) {
        if (uris[0] == null)
            return null;
        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_ALL_FORMATS).build();
        FirebaseVisionImage image = null;
        try {
            image = FirebaseVisionImage.fromFilePath(context, uris[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector();

        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                        // Task completed successfully
                        if (barcodes.isEmpty())
                            txtBarcode.setText("no");


                        else txtBarcode.setText("yes");
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ...
                    }
                });
        return null;
    }
    @Override
    protected void onPostExecute(Boolean res){

    }
}
