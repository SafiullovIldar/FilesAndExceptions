package com.javalearn;

import com.javalearn.enums.Command;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Application {

    private static final String DELIMITER = ",";

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter;
        String command;

        while (true){
            try {
                command = reader.readLine();
                String[] theCommand = command.split(" ");
                if (command.equals(Command.HELP.getName())){
                    Command[] values = Command.values();
                    for (Command value : values) {
                        System.out.println(value.getName());
                    }
                }
                else if (theCommand.length == 2 && theCommand[0].equals(Command.CREATE_FILE.getName())){
                    String filePath = theCommand[1];
                    File file = new File(filePath);
                    file.createNewFile();
                }
                else if (theCommand.length == 3 && theCommand[0].equals(Command.WRITE_FILE.getName())){
                    String filePath = theCommand[1];
                    Path path = Paths.get(filePath);
                    boolean exists = Files.exists(path);

                    if (exists){
                        fileWriter = new FileWriter(filePath, false);
                        String message = theCommand[2];
                        fileWriter.write(message);
                        fileWriter.flush();
                    } else {
                        System.out.println("There is no such a file, you have to create it");
                    }

                }
                else if (theCommand.length == 4 && (theCommand[0] + " " + theCommand[1]).equals(Command.WRITE_FILE_APPEND.getName())){
                    String filePath = theCommand[2];
                    Path path = Paths.get(filePath);
                    boolean exists = Files.exists(path);
                    if (exists){
                        fileWriter = new FileWriter(filePath, true);
                        String message = theCommand[3];
                        fileWriter.write(message);
                        fileWriter.flush();
                    } else {
                        System.out.println("There is no such a file, you have to create it");
                    }

                }
                else if (theCommand.length == 3 && theCommand[0].equals(Command.CP.getName())){
                    Path original = Paths.get(theCommand[1]);
                    Path copied = Paths.get(theCommand[2]);
                    Files.copy(original, copied, StandardCopyOption.REPLACE_EXISTING);
                }
                else if (theCommand.length == 4 && (theCommand[0] + " " + theCommand[1]).equals(Command.CP_APPEND.getName())){
                    File original = new File(theCommand[2]);
                    File copied = new File(theCommand[3]);

                    FileReader reader1 = new FileReader(original);
                    FileWriter writer = new FileWriter(copied, true);

                    while (reader1.ready()){
                        int data = reader1.read();
                        writer.write(data);
                    }
                    reader1.close();
                    writer.close();
                }
                else if (theCommand.length == 2 && theCommand[0].equals(Command.DELETE_FILE.getName())){
                    Files.delete(Paths.get(theCommand[1]));
                }
                else if (theCommand.length == 3 && theCommand[0].equals(Command.ZIP.getName())){
                    String[] filesToZip = theCommand[2].split(DELIMITER);
                    FileOutputStream fos = new FileOutputStream(theCommand[1]);
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
                }
                else if (theCommand.length == 2 && theCommand[0].equals(Command.CAT.getName())){
                    File file = new File(theCommand[1]);
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null){
                        System.out.println(line);
                    }
                }
                else {
                    System.out.println("There is no such a command, try again");
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
