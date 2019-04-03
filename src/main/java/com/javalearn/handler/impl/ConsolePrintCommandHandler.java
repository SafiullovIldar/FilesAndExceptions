package com.javalearn.handler.impl;

import com.javalearn.handler.CommandHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class  ConsolePrintCommandHandler implements CommandHandler {

    @Override
    public void execute(List<String> args, Set<String> options) {
        File file = new File(args.get(0));

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
