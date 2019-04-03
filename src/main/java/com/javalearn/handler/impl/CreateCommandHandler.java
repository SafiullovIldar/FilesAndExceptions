package com.javalearn.handler.impl;

import com.javalearn.handler.CommandHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CreateCommandHandler implements CommandHandler {

    @Override
    public void execute(List<String> args, Set<String> options) {
        String filePath = args.get(0);
        File file = new File(filePath);
        try {
            boolean newFile = file.createNewFile();
            if (newFile)
                System.out.println("There is no such a filePath");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
