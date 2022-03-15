package com.fzc.lalw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author PerfectFu
 */
@SpringBootApplication
@MapperScan({"com.fzc.lalw.mapper"})
public class LalwApplication {
    public static void main(String[] args) {
        SpringApplication.run(LalwApplication.class, args);
    }

}
