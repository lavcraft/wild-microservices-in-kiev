package ru.jeeconf

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class InsuranceApplication {
  public static final int DEFAULT_PADDING = 50

  public static void main(String[] args) {
    println 'Starting'.center(DEFAULT_PADDING, '=')
    SpringApplication.run InsuranceApplication, args
    println 'Started'.center(DEFAULT_PADDING, '=')
  }

  @RequestMapping(value = '/fee', method = RequestMethod.GET)
  int fee() {
    log.info 'insurance process'

    return new Random().nextInt(100)
  }
}