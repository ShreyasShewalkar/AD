package com.example.expt9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageHelper {

    // Write data to a file
    public static void writeToFile(File file, String data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
        }
    }

    // Read data from a file
    public static String readFromFile(File file) {
        if (!file.exists()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file)) {
            int ch;
            while ((ch = fis.read()) != -1) {
                stringBuilder.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Debugging Log
        String result = stringBuilder.toString();
        System.out.println("Read from file: " + result);
        return result;
    }

}
