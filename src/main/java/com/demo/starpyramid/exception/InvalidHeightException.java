package com.demo.starpyramid.exception;

import com.demo.common.BadRequestException;

import java.io.Serial;

public class InvalidHeightException extends BadRequestException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidHeightException(int height) {
        super("Invalid height: " + height + ". Height must be a positive integer.");
    }
}
