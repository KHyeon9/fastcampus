package org.example;

@FunctionalInterface
public interface PasswordGenerator {
    // 인터페이스를 통해서 의존성(결합도)을 낮춘다.
    String generatorPassword();
}
