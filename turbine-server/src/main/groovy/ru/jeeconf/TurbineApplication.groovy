package ru.jeeconf

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream

@EnableDiscoveryClient
@EnableTurbineStream
@SpringBootApplication
public class TurbineApplication {
    public static final int DEFAULT_PADDING = 50

    public static void main(String[] args) {
        println 'Starting'.center(DEFAULT_PADDING, '=')
        SpringApplication.run TurbineApplication, args
        println 'Started'.center(DEFAULT_PADDING, '=')
    }
}