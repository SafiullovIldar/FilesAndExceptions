package com.javalearn.handler.impl;

import com.javalearn.handler.CommandHandler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;

public class CopyCommandHandler implements CommandHandler {

    @Override
    public void execute(List<String> args, Set<String> options) {
        if (!options.isEmpty() && options.contains("-a"))
            copyWithAppend(args);
        else
            copy(args);
    }

    private static void copy(List<String> args) {
        Path original = Paths.get(args.get(0));
        Path copied = Paths.get(args.get(1));

        try {
            Files.copy(original, copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void copyWithAppend(List<String> args){

        File original = new File(args.get(0));
        File copied = new File(args.get(1));

        try {
            FileReader reader = new FileReader(original);
            FileWriter writer = new FileWriter(copied, true);

            while (reader.ready()){
                int data = reader.read();
                writer.write(data);
            }
            reader.close();
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
