package com.javalearn.handler.impl;

import com.javalearn.enums.Command;
import com.javalearn.handler.CommandHandler;

import java.util.List;
import java.util.Set;

public class HelpCommandHandler implements CommandHandler {

    @Override
    public void execute(List<String> args, Set<String> options) {
        Command[] values = Command.values();
        for (Command value : values) {
            System.out.println(value.getName());
        }
    }
}
