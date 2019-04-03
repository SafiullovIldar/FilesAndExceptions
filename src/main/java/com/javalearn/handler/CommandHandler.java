package com.javalearn.handler;

import java.util.List;
import java.util.Set;

public interface CommandHandler {

    void execute(List<String> args, Set<String> options);
}
