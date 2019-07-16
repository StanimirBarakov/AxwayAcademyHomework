package com.axway.academy.utils.exceptions;

import java.io.IOException;
import java.net.SocketException;

public class NoSuchFileException extends IOException {

    public NoSuchFileException(String message) {
        super(message);
    }
}
