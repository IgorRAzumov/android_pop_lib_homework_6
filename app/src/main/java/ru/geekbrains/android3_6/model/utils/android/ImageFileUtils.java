package ru.geekbrains.android3_6.model.utils.android;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ru.geekbrains.android3_6.App;
import timber.log.Timber;

public class ImageFileUtils {

    public static String MD5(String s) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(s.getBytes(), 0, s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    public static float getSizeKb(File directoryPath) {
        return getFileOrDirSize(directoryPath) / 1024f;
    }

    public static void deleteFileOrDirRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteFileOrDirRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }

    public static long getFileOrDirSize(File f) {
        long size = 0;
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                size += getFileOrDirSize(file);
            }
        } else {
            size = f.length();
        }
        return size;
    }

    public static File getDirectory(String folderName) {
        return new File(App.getInstance().getExternalFilesDir(null) + "/" + folderName);
    }

    public static File saveImage(String key, Bitmap image, String directoryName) {
        File directory = ImageFileUtils.getDirectory(directoryName);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + directory.toString());
        }

        final String fileFormat = key.contains(".jpg") ? ".jpg" : ".png";
        File imageFile = new File(directory, ImageFileUtils.MD5(key) + fileFormat);

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imageFile);
            image.compress(fileFormat.equals("jpg")
                    ? Bitmap.CompressFormat.JPEG
                    : Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            Timber.d("Failed to save image");
            if (imageFile.exists()) {
                imageFile.delete();
                imageFile = null;
            }
        }
        if (imageFile == null) {
            return null;
        }
        return imageFile;
    }
}
