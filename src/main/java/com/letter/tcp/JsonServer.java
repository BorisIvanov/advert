package com.letter.tcp;

/**
 * Created by berty on 04.07.14.
 */
public class JsonServer {

    public String get(String input) {
        if ("FAIL".equals(input)) {
            throw new RuntimeException("Failure Demonstration");
        }
        return "echo:" + input;
    }

}
