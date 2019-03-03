package com.example.namxuan.mylastadnroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView fixBarcode;
    TextView fixPeople;
    TextView fixFacial;
    TextView fixSmile;
    TextView fixEye;

    TextView txtBarcode;
    TextView txtNumperson;
    TextView txtSmile;
    TextView txtEye ;

    Button btnPickPhoto ;
    ImageView imageview;

    ImageView holdTarget;

    private Uri uri = null;
    private Bitmap bitmap;
    private Target target;



    private  void prepare(){
        fixBarcode = (TextView)findViewById(R.id.fixBarcode);
        fixPeople = (TextView)findViewById(R.id.fixPerson);
        fixFacial = (TextView)findViewById(R.id.fixFacial);
        fixSmile = (TextView)findViewById(R.id.fixSmile);
        fixEye = (TextView) findViewById(R.id.fixEyes);

        txtBarcode = (TextView)findViewById(R.id.txtBarcode);
        txtNumperson = (TextView) findViewById(R.id.txtNumPeople);
        txtSmile = (TextView)findViewById(R.id.txtSmile);
        txtEye = (TextView)findViewById(R.id.txtEyes);

        btnPickPhoto = (Button)findViewById(R.id.btnPickPhoto);
        imageview = (ImageView) findViewById(R.id.imageView);
        fixBarcode.setText("Barcodes:");
        fixPeople.setText("Persons: ");
        fixFacial.setText("Facial Features");
        fixSmile.setText("Smile:    ");
        fixEye.setText("Eyes:   ");
        btnPickPhoto.setText("PICK PHOTO");
        txtBarcode.setText("no");
        txtNumperson.setText("0");
        txtSmile.setText("no");
        txtEye.setText("no");
        FirebaseApp.initializeApp(this);
    }

    private void loadImage(){
        txtBarcode.setText("no");
        txtNumperson.setText("0");
        txtSmile.setText("no");
        txtEye.setText("no");
        uri = null;
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private void process(){
        FirebaseApp.initializeApp(MainActivity.this) ;
        //FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
         //       .getVisionBarcodeDetector();
        btnPickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
          //      BarCode barCode = new BarCode(MainActivity.this, txtBarcode);
           //     barCode.doInBackground(uri);
            }
        });

    }
    private void getImageFromUri(Uri uri){
//            target = new Target(){
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                MainActivity.this.bitmap = Bitmap.createBitmap(bitmap);
//
//                //processImage();
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//
//`            @Override
//`            public void onPrepareLoad(Drawable placeHolderDrawable) {
//`
//`            }
//        };
        //holdTarget = (ImageView) findViewById(R.id.holdTarget);
        //holdTarget.setTag(target);
        //Picasso.get().load(uri).resize(imageview.getMaxWidth(), imageview.getMaxHeight()).into(target) ;
        //Picasso.get().load(uri).into(target) ;
        //holdTarget.setTag(target);

  //     imageview.buildDrawingCache();
  //     bitmap = imageview.getDrawingCache();
     //BitmapDrawable drawable = (BitmapDrawable) imageview.getDrawable();
//           new Thread(new Runnable() {
//               @Override
//               public void run() {
//                   try {
//                       //bitmap = Picasso.get().load(MainActivity.this.uri).resize(MainActivity.this.imageview.getMaxWidth(), MainActivity.this.imageview.getMaxHeight()).get();
//                       //bitmap = Picasso.get().load(MainActivity.this.uri).get();
//
//                  //    processImage();
//                   } catch (Exception e) {
//                       e.printStackTrace();
//                   }
//               }
//           }).start();
//        //Picasso.get().load(uri).get();
//        BitmapRegionDecoder decoder = null;
//        try {
//            decoder = BitmapRegionDecoder.newInstance(MainActivity.this.uri.getPath(), false);
//        } catch (IOException e) {
//            e.printStackTrace();
 //       }
        Log.d("MCC", MainActivity.this.uri.getPath());
  //      bitmap = decoder.decodeRegion(new Rect(10,10,50,50),null);
        // try {
//            InputStream is = getContentResolver().openInputStream(uri);
//            bitmap = BitmapFactory.decodeStream(is);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        processImage();
    }
    private FirebaseVisionImage getImagetoFirebase()  {
        FirebaseVisionImage image = null;
        try {
            //image = FirebaseVisionImage.fromFilePath(MainActivity.this, uri);
           image = FirebaseVisionImage.fromBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    private void processImage(){
        barCodeDetect();
        peopleDetect();

    }
    private void peopleDetect(){
       FirebaseVisionFaceDetectorOptions options =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();
        FirebaseVisionImage image = getImagetoFirebase();
        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(options);
        Task<List<FirebaseVisionFace>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionFace>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionFace> faces) {
                                        // Task completed successfully
                                        txtNumperson.setText(new Integer(faces.size()).toString());
                                        float finalSmileProb = 0, finalLeftEyeOpened = 0, finalRightEyeOpened = 0;
                                        for (FirebaseVisionFace face : faces){
                                            if (face.getSmilingProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                                                float smileProb = face.getSmilingProbability();
                                                finalSmileProb = finalSmileProb + smileProb;
                                            }
                                            if (face.getLeftEyeOpenProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                                                float leftEyeOpenProb = face.getRightEyeOpenProbability();
                                                finalLeftEyeOpened = finalLeftEyeOpened + leftEyeOpenProb;
                                            }
                                            if (face.getRightEyeOpenProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY){
                                                float rightEyeOpenProb = face.getRightEyeOpenProbability();
                                                finalRightEyeOpened = finalRightEyeOpened + rightEyeOpenProb;
                                            }
                                        }
                                        if (finalSmileProb >= 0.5){
                                           txtSmile.setText("yes");
                                        }
                                        else txtSmile.setText("no");
                                        if (finalLeftEyeOpened >= 0.5 && finalRightEyeOpened >= 0.5){
                                            txtEye.setText("yes");

                                        }
                                        else txtEye.setText("no");
                                        // ...
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });


    }
    private void barCodeDetect(){
        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_ALL_FORMATS).build();

        FirebaseApp.initializeApp(this) ;
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options);

        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(getImagetoFirebase())
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                        // Task completed successfully
                        if (barcodes.isEmpty())
                            txtBarcode.setText(FALSE);
                        else txtBarcode.setText(TRUE);

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            //Picasso.get().load(uri).fit().into(imageview);
            try{
       //         InputStream is = getContentResolver().openInputStream(uri);
        //        bitmap = BitmapFactory.decodeStream(is);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            bitmap = decodeSampledBitmapFromResource(uri);
            imageview.setImageBitmap(bitmap);
            //bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
            //processImage();
            //getImageFromUri(uri);

        }
        processImage();
    }
    public Bitmap decodeSampledBitmapFromResource(Uri uri) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream is = null;

        try {
            is = getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(is, null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Calculate inSampleSize
        int maxWidth = imageview.getWidth();
        int maxHeigh = imageview.getHeight();
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeigh);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        try{
            is = getContentResolver().openInputStream(uri);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is, null, options);
    }

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepare();
        process();
    }
    private final int PICK_IMAGE_REQUEST = 1;
    private final String TRUE = "yes";
    private final String FALSE = "no";
}
