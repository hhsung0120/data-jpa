package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        //실제로직에선 세션이나 토큰에서 꺼내서 ID 를 넣어주면
        //Spring 에서   @CreatedBy,     @LastModifiedBy 호출
        //인터페이스에서 메서드 하나면 람다로 바꿀수 있음
        return () -> Optional.of(UUID.randomUUID().toString());
    }
}
