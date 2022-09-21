package edu.school21.cinema.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class Utils {
    public static boolean createFile(MultipartFile file, String path, String extension, UUID uuid) {
        try {
            byte[] barr = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream((new FileOutputStream(path + "/" + uuid + "." + extension)));
            stream.write(barr);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static String convert(long size) {
        String cnt_size;
        double size_kb = (double) size / 1000;
        double size_mb = size_kb / 1000;

        if (size_kb > 999) {
            cnt_size = String.format("%.2fMB", size_mb);
        } else {
            cnt_size = String.format("%.2fKB", size_kb);
        }
        return cnt_size;
    }
}
