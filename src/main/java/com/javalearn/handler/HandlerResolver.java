package com.javalearn.handler;

public interface HandlerResolver {

    CommandHandler resolve(String command);
}
