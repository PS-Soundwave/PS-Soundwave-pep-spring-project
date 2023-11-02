package com.example.exception;

/* CM: Although this is in principle a runtime exception (cf. IllegalArgumentException), custom exceptions are all checked
 * so that exceptions do not leak past the controller */
/**
 * Thrown when an account is invalid, e.g. by having an empty username
 */
public class InvalidAccountException extends Exception {}
