package com.example.backend.utils;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

public class SHA256 {
    private static final String SHA_256_ALGORITHM = "SHA-256";

    /**
     * 加密方法
     *
     * @param data：明文
     * @return 密文
     * @throws Exception
     */
    public static @NotNull String encrypt(@NotNull String data) throws Exception {
        //获取SHA-256算法实例
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        //计算散列值
        byte[] digest = messageDigest.digest(data.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转换为15进制字符串
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return stringBuilder.toString();
    }
}