package cn.hang.neuq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.hang.neuq.mapper")
public class NeuqInfoBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeuqInfoBootApplication.class, args);
    }

}

