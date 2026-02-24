package com.utc.project.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {

    private final Map<String, VerificationDetails> cache = new ConcurrentHashMap<>();

    public void saveCode(String correo, String codigo) {
        cache.put(correo, new VerificationDetails(codigo, LocalDateTime.now().plusMinutes(15)));
    }

    public boolean verifyCode(String correo, String codigo) {
        VerificationDetails details = cache.get(correo);
        if (details != null && details.codigo.equals(codigo)) {
            if (details.expiration.isAfter(LocalDateTime.now())) {
                cache.remove(correo);
                return true;
            }
            cache.remove(correo);
        }
        return false;
    }

    private static class VerificationDetails {
        String codigo;
        LocalDateTime expiration;

        VerificationDetails(String codigo, LocalDateTime expiration) {
            this.codigo = codigo;
            this.expiration = expiration;
        }
    }
}