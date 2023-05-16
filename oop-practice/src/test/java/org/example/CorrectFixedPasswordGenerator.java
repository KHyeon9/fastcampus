package org.example;

public class CorrectFixedPasswordGenerator implements PasswordGenerator{

    @Override
    public String generatorPassword() {
        return "abcdefgh"; // 8글자 생성
    }
}
