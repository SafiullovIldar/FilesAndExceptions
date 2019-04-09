package com.javalearn.handler.impl;

import com.javalearn.handler.CommandHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class WriteCommandHandler implements CommandHandler {

    @Override
    public void execute(List<String> args, Set<String> options) {
        int filePathIndex = 0;
        int messageIndex = 1;
        boolean append = false;

        if (!options.isEmpty() && options.contains("-a")) {
            append = true;
        }

        String filePath = args.get(filePathIndex);
        Path path = Paths.get(filePath);
        boolean exists = Files.exists(path);

        if (exists) {
            try (FileWriter fileWriter = new FileWriter(filePath, append)) {

                String message = args.get(messageIndex);
                fileWriter.write(message);
                fileWriter.flush();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There is no such a file, you have to create it first");
        }
    }
}
