package com.javalearn.listener;

import com.javalearn.handler.CommandHandler;
import com.javalearn.handler.HandlerResolver;
import com.javalearn.handler.HandlerResolverImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class CommandListener {

    public static void run(){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HandlerResolver resolver = HandlerResolverImpl.getInstance();
        CommandHandler handler;

        while (true) {
            try {
                String[] input = reader
                        .readLine()
                        .split(" ");

                String command = input[0];

                Set<String> options = Arrays
                        .stream(input)
                        .filter(i -> i.startsWith("-"))
                        .collect(Collectors.toSet());

                List<String> args = Arrays
                        .stream(input)
                        .filter(i -> !i.startsWith("-") && !i.equals(command))
                        .collect(Collectors.toList());

                handler = resolver.resolve(command);

                if (handler == null)
                    System.out.println("There is no such a command");
                else
                    handler.execute(args, options);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
