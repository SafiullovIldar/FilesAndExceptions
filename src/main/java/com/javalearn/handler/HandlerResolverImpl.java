package com.javalearn.handler;

import com.javalearn.enums.Command;
import com.javalearn.handler.impl.*;

import java.util.HashMap;
import java.util.Map;

public class HandlerResolverImpl implements HandlerResolver {

    private static HandlerResolverImpl instance;
    private static final Map<String, CommandHandler> COMMAND_HANDLER_MAP = new HashMap<>();

    private HandlerResolverImpl() {

        COMMAND_HANDLER_MAP.put(Command.HELP.getName(), new HelpCommandHandler());
        COMMAND_HANDLER_MAP.put(Command.CREATE_FILE.getName(), new CreateCommandHandler());
        COMMAND_HANDLER_MAP.put(Command.WRITE_FILE.getName(), new WriteCommandHandler());
        COMMAND_HANDLER_MAP.put(Command.DELETE_FILE.getName(), new DeleteCommandHandler());
        COMMAND_HANDLER_MAP.put(Command.ZIP.getName(), new ZipPackCommandHandler());
        COMMAND_HANDLER_MAP.put(Command.CP.getName(), new CopyCommandHandler());
        COMMAND_HANDLER_MAP.put(Command.CAT.getName(), new ConsolePrintCommandHandler());
    }

    public static HandlerResolverImpl getInstance(){

        if (instance == null){
            instance = new HandlerResolverImpl();
        }
        return instance;
    }

    @Override
    public CommandHandler resolve(String command) {

        return COMMAND_HANDLER_MAP.get(command);
    }
}
