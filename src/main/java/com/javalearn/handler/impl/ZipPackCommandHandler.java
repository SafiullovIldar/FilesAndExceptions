package com.javalearn.handler.impl;

import com.javalearn.handler.CommandHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipPackCommandHandler implements CommandHandler {

    private static final String DELIMITER = ",";

    @Override
    public void execute(List<String> args, Set<String> options) {
        String[] filesToZip = args.get(1).split(DELIMITER);

        try {
            FileOutputStream fos = new FileOutputStream(args.get(0));
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            for (String srcFile : filesToZip) {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
