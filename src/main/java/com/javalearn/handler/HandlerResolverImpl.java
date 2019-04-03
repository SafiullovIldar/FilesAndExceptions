package com.javalearn.handler;

import com.javalearn.enums.Command;
import com.javalearn.handler.impl.*;

public class HandlerResolverImpl implements HandlerResolver {

    @Override
    public CommandHandler resolve(String command) {
        CommandHandler handler = null;

        if (command.equals(Command.HELP.getName())){
            handler = new HelpCommandHandler();
        } else if (command.equals(Command.CREATE_FILE.getName())){
            handler = new CreateCommandHandler();
        } else if (command.equals(Command.WRITE_FILE.getName())){
            handler = new WriteCommandHandler();
        } else if (command.equals(Command.DELETE_FILE.getName())){
            handler = new DeleteCommandHandler();
        } else if (command.equals(Command.ZIP.getName())){
            handler = new ZipPackCommandHandler();
        } else if (command.equals(Command.CP.getName())){
            handler = new CopyCommandHandler();
        } else if (command.equals(Command.CAT.getName())){
            handler = new ConsolePrintCommandHandler();
        }

        return handler;
    }
}
