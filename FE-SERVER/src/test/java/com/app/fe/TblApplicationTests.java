package com.app.fe;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TblApplicationTests {

    @Test
    @DisplayName("평문을 JWT를 사용하기 위한 SecretKey로 변환 한다.")
    void jwtSecretKey() {
        String normalKey = "tbltbltbltbltbltbltbltbltbl";
        String jwtKeyEncoded = Base64.getEncoder().encodeToString(normalKey.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtKeyEncoded.getBytes());

        System.out.println(jwtKeyEncoded);
        assertThat(secretKey).isNotNull();
    }

    @Test
    @DisplayName("두 날짜 사이의 값을 구한다.")
    void untilLocalDate() {
        LocalDate from = LocalDate.of(2024, 2, 6);
        LocalDate to = LocalDate.of(2024, 2, 7);

        System.out.println("FROM : " + from + " To : " + to + " = " + from.until(to).plusDays(1L).getDays());
    }
}
