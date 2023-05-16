package org.example;

public class User {
    private String password;

    public String getPassword() {
        return password;
    }

    public void initPassword(PasswordGenerator passwordGenerator){
        // as-is방식
        // RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
        // String randomPassword = randomPasswordGenerator.generatorPassword();

        //to-be방식
        String password = passwordGenerator.generatorPassword();

        /**
         * 비밀번호는 8자 이상 12자 이하여야 한다.
         */

        int length = password.length();

        if (length >= 8 && length <= 12){
            this.password = password;
        }


    }
}
