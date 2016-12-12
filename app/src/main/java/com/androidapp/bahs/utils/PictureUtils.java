package com.androidapp.bahs.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.service.db.AppSharedPreferences;
import com.androidapp.bahs.service.utils.AlertUtils;
import com.androidapp.bahs.service.utils.Constants;
import com.androidapp.bahs.service.utils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;


public class PictureUtils {

    public static final int PICK_FROM_CAMERA = 1;
    public static final int PICK_FROM_FILE = 3;
    public static final int CROP_FROM_CAMERA = 2;
    public static final String FILE_NAME = "pic_temp.png";
    public static final String APP_DIR_MAIN = "Performix/Images/";
    private Context mContext;

    public PictureUtils(Context ctx) {
        mContext = ctx;
    }

    public File createDirectory(FragmentActivity context, String folderName) {
        File mainDir = new File(Environment.getExternalStorageDirectory().toString() + APP_DIR_MAIN + folderName);
        if (!mainDir.exists()) {
            mainDir.mkdirs();
        }
        return mainDir;
    }


    public int getAvailableExternalMemorySize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return (int) ((availableBlocks * blockSize) / (1024 * 1024));
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public Bitmap getRoundedShape(Bitmap bitmap) {
        if (bitmap != null) {
            Bitmap targetBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(targetBitmap);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);

            Path path = new Path();
            path.addCircle(((float) bitmap.getWidth() - 1) / 2, ((float) bitmap.getHeight() - 1) / 2,
                    (Math.min(((float) bitmap.getWidth()), ((float) bitmap.getHeight())) / 2), Path.Direction.CCW);
            canvas.clipPath(path);
            Bitmap sourceBitmap = bitmap;
            Rect rect = new Rect(0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight());
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return targetBitmap;
        }
        return null;

    }

    public Bitmap getSampledBitmap(FragmentActivity context, File file) {
        Bitmap bitmap = null;
        ExifInterface exif;
        try {
            exif = new ExifInterface(file.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int angle = 0;

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }

            Matrix mat = new Matrix();
            mat.postRotate(angle);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            // Don't read the pixel array into memory, only read the picture information
            opts.inSampleSize = 2;
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
            // Get a picture from the Options resolution
            int imageHeight = opts.outHeight;
            int imageWidth = opts.outWidth;
            RefrenceWrapper refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(context);
            int windowHeight = refrenceWrapper.getmDeviceUtilHandler().getScreenHeight(context);
            int windowWidth = refrenceWrapper.getmDeviceUtilHandler().getScreenWidth(context);

            // Calculation of sampling rate
            int scaleX = imageWidth / windowWidth;
            int scaleY = imageHeight / windowHeight;
            int scale = 1;
            // The sampling rate in accordance with the direction of maximum prevail
            if (scaleX > scaleY && scaleY >= 1) {
                scale = scaleX;
            }
            if (scaleX < scaleY && scaleX >= 1) {
                scale = scaleY;
            }

            // False read the image pixel array into memory, in accordance with the
            // sampling rate set
            opts.inJustDecodeBounds = false;
            // Sampling rate
            opts.inSampleSize = scale;
            opts.inMutable = true;
            Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
            bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public long getImageSize(long bytes) {
        long sizeInMb = bytes / (1024);
        return sizeInMb;
    }


    public Bitmap convertFileIntoBitmap(FragmentActivity context, File file) {
        if (getImageSize(file.length()) > 512) {
            return compressImage(file.getAbsolutePath());
        } else {
            return getSampledBitmap(context, file);
        }
    }

    public byte[] getByteArray(Bitmap bitmap) {

        if (bitmap == null) {
            return null;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public Bitmap createScaledImage(File mFile) {

        Bitmap bitmap = null;
        ExifInterface exif;
        try {
            exif = new ExifInterface(mFile.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int angle = 0;

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }

            Matrix mat = new Matrix();
            mat.postRotate(angle);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(mFile), null, options);
            if (bmp.getWidth() > bmp.getHeight())
                bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getHeight(), bmp.getHeight(), mat, true);
            else
                bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getWidth(), mat, true);
        } catch (Exception e) {
            LogUtils.error(e);
        }
        if (bitmap != null)
            return bitmap;

        else
            return null;
    }

    public File createDirectory() {

        File mainDir = new File(Environment.getExternalStorageDirectory(), APP_DIR_MAIN);
        if (!mainDir.exists()) {
            mainDir.mkdirs();
        }
        return mainDir;
    }

    public File getFileFromUri(Context context, Uri uri) {

        if (uri == null) {
            return null;
        }

        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor c = context.getContentResolver().query(uri, filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        String picturePath = c.getString(columnIndex);
        File file = new File(picturePath);
        c.close();
        LogUtils.debug("image Path", picturePath + "");
        return file;
    }

    public int getAvailableInternalMemorySize() {
        File path = mContext.getFilesDir();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return (int) ((availableBlocks * blockSize) / (1024 * 1024));
    }

    public File saveImage(Bitmap bitmap, String name, String directory) {
        File sd;
        int flag = 0;
        String dirs = APP_DIR_MAIN + directory;
        String curremtMillis = name;
        boolean b = isExternalStorageWritable();
        AppSharedPreferences prefs = AppSharedPreferences.getInstance();
        if (b == true) {
            int i = getAvailableExternalMemorySize();
            if (i > 4) {
                prefs.commitStringValue(dirs, "external");
                LogUtils.error("Saving Image in external memory", "" + b);
                sd = Environment.getExternalStorageDirectory();
            } else {
                int j = getAvailableInternalMemorySize();
                LogUtils.error("j", "" + j);
                if (j <= 4)
                    flag = 1;
                prefs.commitStringValue(dirs, "internal");
                sd = mContext.getFilesDir();
            }
        } else {
            int j = getAvailableInternalMemorySize();
            LogUtils.error("j", "" + j);
            if (j <= 5)
                flag = 1;
            sd = mContext.getFilesDir();
            prefs.commitStringValue(dirs, "internal");
        }
        File dest = null;
        if (flag == 0) {
            if (!new File(sd, dirs).exists()) {
                System.out.println("dirs made.." + new File(sd, dirs).mkdirs());
            }
            dest = new File(new File(sd, dirs), curremtMillis + ".png");
            try {
                FileOutputStream out;
                out = new FileOutputStream(dest);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                LogUtils.error("Saving Image", "done file write");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            if (mContext instanceof Activity) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertUtils.showToast(mContext, "Memory Full");
                    }
                });
            }
        }
        return dest;
    }

    public Uri getLocalBitmapUri(View imageView) {
        // Extract Bitmap from ImageView drawable
        Bitmap bmp = null;
//        if (imageView instanceof PentagonalImageViewFullView) {
//            bmp = ((PentagonalImageViewFullView) imageView).getBitmapSet();
//        } else if (imageView instanceof CustomImageView) {
//            bmp = ((CustomImageView) imageView).getBitmapSet();
//        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            bmpUri = Uri.fromFile(saveImage(bmp, System.currentTimeMillis() + "", Constants.IMAGE_DIRECTORY_CONSTANTS.SHARED_IMAGES));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public void shareImagesFromIntent(FragmentActivity fragActivity, HashSet<View> viewHashset, String text) {
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        Iterator iterator = viewHashset.iterator();
        while (iterator.hasNext()) {
            View val = (View) iterator.next();
            imageUris.add(getLocalBitmapUri(val));
        }
        Intent shareIntent = new Intent();
        if (imageUris.size() > 1) {
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        } else if (imageUris.size() == 1) {
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, text);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUris.get(0));
        }
        shareIntent.setType("image/*");
        fragActivity.startActivity(Intent.createChooser(shareIntent, "Share images to.."));
    }

    public void shareTextFromIntent(FragmentActivity fragActivity, String text) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.setType("text/*");
        fragActivity.startActivity(Intent.createChooser(shareIntent, "Share images to.."));
    }

    public ArrayList<File> getfile(File dir) {
        ArrayList<File> fileList = new ArrayList<>();
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    fileList.add(listFile[i]);
                    getfile(listFile[i]);

                } else {
                    if (listFile[i].length() > 0 && (listFile[i].getName().endsWith(".png")
                            || listFile[i].getName().endsWith(".jpg")
                            || listFile[i].getName().endsWith(".jpeg")
                            || listFile[i].getName().endsWith(".gif")))

                    {
                        fileList.add(listFile[i]);
                    }
                }

            }
        }
        sortList(fileList);
        return fileList;
    }

    private void sortList(ArrayList<File> unsortedList) {
        Collections.sort(unsortedList, new Comparator<File>() {
            public int compare(File s1, File s2) {
                if (s1.lastModified() < s2.lastModified())
                    return -1;
                if (s1.lastModified() == s2.lastModified())
                    return 0;
                return 1;
            }
        });
    }

    public ArrayList<File> getImageFromExternalStorage(String directoryName, Context c) {
        Boolean directoryexsist = true;
        String dirs = APP_DIR_MAIN + directoryName;
        AppSharedPreferences prefs = AppSharedPreferences.getInstance();
        String memory = prefs.getStringValue(dirs);
        File sdCard = null;
        if (memory.equals("external")) {
            sdCard = Environment.getExternalStorageDirectory();
            LogUtils.error("pick external", "");
        } else {
            sdCard = c.getFilesDir();
            directoryexsist = false;
            LogUtils.error("pick internal", "");
        }
        File directory = new File(sdCard.getAbsolutePath() + "/" + dirs);
        if (!(directory.exists())) {
            if (directoryexsist == false)
                sdCard = Environment.getExternalStorageDirectory();
            else
                sdCard = c.getFilesDir();
        }
        ArrayList<File> fileList = getfile(directory);

        return fileList;
    }

    public String getImagePath(String name_of_image, Context ctx) {
        Boolean directoryexsist = true;
        AppSharedPreferences prefs = AppSharedPreferences.getInstance();
        String memory = prefs.getStringValue(name_of_image);
        File sdCard = null;
        if (memory.equals("external")) {
            sdCard = Environment.getExternalStorageDirectory();
            LogUtils.error("pick external", "");
        } else {
            sdCard = ctx.getFilesDir();
            directoryexsist = false;
            LogUtils.error("pick internal", "");
        }
        File directory = new File(sdCard.getAbsolutePath() + APP_DIR_MAIN);
        if (!(directory.exists())) {
            if (directoryexsist == false)
                sdCard = Environment.getExternalStorageDirectory();
            else
                sdCard = ctx.getFilesDir();
        }
        File file = new File(directory, name_of_image + ".png"); // or
        System.out.println("image..." + name_of_image + ".png"); // any
        return file.getAbsolutePath();
    }

    public Bitmap scaleDownBitmap(Bitmap photo, int newHeight, FragmentActivity context) {
        final float densityMultiplier = context.getResources().getDisplayMetrics().density;
        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));
        photo = Bitmap.createScaledBitmap(photo, w, h, true);
        return photo;
    }

    public Bitmap compressImage(String filePath) {

        Bitmap scaledBitmap = null;
        String filename = null;

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();

            // by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
            // you try the use the bitmap here, you will get null.
            options.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

            int actualHeight = options.outHeight;
            int actualWidth = options.outWidth;

            if (actualHeight == 0) {
                actualHeight = bmp.getHeight();
            }
            if (actualWidth == 0) {
                actualWidth = bmp.getWidth();
            }

            // max Height and width values of the compressed image is taken as 816x612
            RefrenceWrapper refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);
            int windowHeight = refrenceWrapper.getmDeviceUtilHandler().getScreenHeight((Activity) mContext);
            int windowWidth = refrenceWrapper.getmDeviceUtilHandler().getScreenWidth((Activity) mContext);
            float maxHeight = windowHeight;//816.0f;
            float maxWidth = windowWidth;//612.0f;

            System.out.println("heig actualWidth" + actualWidth);
            System.out.println("heig actualHeight" + actualHeight);
            float imgRatio = actualWidth / actualHeight;
            float maxRatio = maxWidth / maxHeight;

            // width and height values are set maintaining the aspect ratio of the image

            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight;
                    actualWidth = (int) (imgRatio * actualWidth);
                    actualHeight = (int) maxHeight;
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth;
                    actualHeight = (int) (imgRatio * actualHeight);
                    actualWidth = (int) maxWidth;
                } else {
                    actualHeight = (int) maxHeight;
                    actualWidth = (int) maxWidth;
                }
            }

            // setting inSampleSize value allows to load a scaled down version of the original image

            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

            // inJustDecodeBounds set to false to load the actual bitmap
            options.inJustDecodeBounds = false;

            // this options allow android to claim the bitmap memory if it runs low on memory
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[16 * 1024];

            // load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);

            float ratioX = actualWidth / (float) options.outWidth;
            float ratioY = actualHeight / (float) options.outHeight;
            float middleX = actualWidth / 2.0f;
            float middleY = actualHeight / 2.0f;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

            // check the rotation of the image and display it properly
            ExifInterface exif;
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

            FileOutputStream out = null;
            filename = filePath;
            out = new FileOutputStream(filename);

            // write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scaledBitmap;

    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    public void openGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, PICK_FROM_FILE);
    }

    public void openCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(createDirectory(), FILE_NAME);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        try {
            activity.startActivityForResult(intent, PictureUtils.PICK_FROM_CAMERA);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Uri getImageUri(String path) {
        return Uri.fromFile(new File(path));
    }

    public interface Crop {
        String URI = "uri";
        String FILE_PATH = "file_path";
    }
}
