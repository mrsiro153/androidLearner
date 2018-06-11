package com.example.nhdoan.doanapp.ultility;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    private Context context;
    private static final String TAG_NAME = "DOANNH_";

    public static ScreenShot getScreenShotInstance(Context context){
        return new ScreenShot(context);
    }
    private ScreenShot(Context context){
        this.context = context;
    }

    //
    public void takeScreenShot(View v) {
        Bitmap b = getBitMapOfView(v.getRootView());
        try {
            File file = saveScreenshotToPicturesFolder(context, b, "myName");
            Toast.makeText(context, "Image saved at " + file.getAbsolutePath(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG_NAME, "ERROR", e);
            e.printStackTrace();
        }
    }

    private Bitmap getBitMapOfView(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);

        // creates immutable clone
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }

    private File saveScreenshotToPicturesFolder(Context context, Bitmap image, String filename)
            throws Exception {
        //
        File bitmapFile = getOutputMediaFile(filename);
        if (bitmapFile == null) {
            throw new NullPointerException("Error creating media file, check storage permissions!");
        }
        FileOutputStream fos = new FileOutputStream(bitmapFile);
        image.compress(Bitmap.CompressFormat.PNG, 90, fos);
        fos.close();

        // Initiate media scanning to make the image available in gallery apps
        MediaScannerConnection.scanFile(context, new String[]{bitmapFile.getPath()},
                new String[]{"image/jpeg"}, null);
        return bitmapFile;
    }

    private File getOutputMediaFile(String filename) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDirectory = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDirectory.exists()) {
            if (!mediaStorageDirectory.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmssSSS").format(new Date());
        File mediaFile;
        String mImageName = filename + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDirectory.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}
