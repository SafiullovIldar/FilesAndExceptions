package com.javalearn.handler.impl;

import com.javalearn.handler.CommandHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class DeleteCommandHandler implements CommandHandler {

    @Override
    public void execute(List<String> args, Set<String> options) {
        try {
            Files.delete(Paths.get(args.get(0)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
