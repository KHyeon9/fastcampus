package org.example;

public class WrongFixedPasswordGenerator implements PasswordGenerator{

    @Override
    public String generatorPassword() {
        return "ab"; // 8글자 생성
    }
}
