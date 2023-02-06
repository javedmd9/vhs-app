package com.vivacom.vhs.utils;



import com.google.common.io.Files;

public class Utility {

    public static String getFileExtension(String fileName) {
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = "." + fileName.substring(index + 1);
        }
        return extension;
    }

    public String getExtensionByGuava(String filename) {
        return Files.getFileExtension(filename);
    }


}
