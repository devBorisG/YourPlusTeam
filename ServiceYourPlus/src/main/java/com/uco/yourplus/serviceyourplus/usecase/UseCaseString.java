package com.uco.yourplus.serviceyourplus.usecase;

public interface UseCaseString<D> {
    void execute(D domain, String token);
}
