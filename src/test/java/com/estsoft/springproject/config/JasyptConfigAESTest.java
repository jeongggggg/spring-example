package com.estsoft.springproject.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JasyptConfigAESTest {
    @Test
    void stringEncryptorTest() {
        String password = "ahjeong921";

        // 암호화
        String encrypted = jasyptEncoding(password);
        System.out.println("암호화된 값: " + encrypted);

        // 복호화
        String decrypted = jasyptDecoding(encrypted);
        System.out.println("복호화된 값: " + decrypted);

        // 원래 값과 복호화된 값이 같은지 확인
        assertEquals(password, decrypted);
    }

    public String jasyptEncoding(String value) {
        String key = "jasypt_key";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        pbeEnc.setSaltGenerator(new RandomSaltGenerator());
        return pbeEnc.encrypt(value);
    }

    public String jasyptDecoding(String encryptedValue) {
        String key = "jasypt_key";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        pbeEnc.setSaltGenerator(new RandomSaltGenerator());
        return pbeEnc.decrypt(encryptedValue);
    }
}