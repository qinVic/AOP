package com.vic.aop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @author qinquan
 * @date 2019/7/29
 */
@Data
@Component
@ConfigurationProperties("jwt.config")
public class JwtUtils {

    /**
     * 签名密钥
     */
    private String key;
    /**
     * 过期时间
     */
    private Long ttl;

    /**
     * 加密密钥
     * @return SecretKeySpec
     */
    public SecretKeySpec generalKey() {
        // 将本地密钥解码
        byte[] decodeKey = Base64.decodeBase64(key);
        // 将解码后的密钥使用AES算法构成一个密钥
        return new SecretKeySpec(decodeKey, 0, decodeKey.length, "AES");
    }

    /**
     * 生成token
     * @param id
     * @param name
     * @param map
     * @return
     */
    public String createToken(String id, String name, Map<String, Object> map) {
        // 过期时间
        Long expireTime = System.currentTimeMillis() + ttl;
        // 生成加密后签名的密钥
        SecretKey secretKey = generalKey();

        return Jwts.builder()
                // JWT_ID
                .setId(id)
                // 主题
                .setSubject(name)
                // 签发时间
                .setIssuedAt(new Date())
                .setClaims(map)
                // 失效时间
                .setExpiration(new Date(expireTime))
                // 签名算法以及密钥
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public Claims parseToken(String token) {
        // 生成加密后签名的密钥
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
