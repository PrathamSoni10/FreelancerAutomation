package com.freelancer.ui.automation.utils;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class CryptoConfig {

    private static FileOperations fileOps = new FileOperations("./encryption.properties");
    private static String salt;
    private static String password;

    public CryptoConfig(){

    }

    public void setPassword(String password) { CryptoConfig.password = password; }

    private static String getPassword() { return password; }

    private static String getSalt() { return salt; }

    public static String encryptToHex(String plainText){
        TextEncryptor encryptor = Encryptors.delux(getPassword(), getSalt());
        return encryptor.encrypt(plainText);
    }

    public static String decryptFromHex(String cipherText){
        TextEncryptor encryptor = Encryptors.delux(getPassword(), getSalt());
        return encryptor.decrypt(cipherText);
    }

    static{
        password = fileOps.getPropertyValue("password");
        salt = fileOps.getPropertyValue("salt");

    }

}
