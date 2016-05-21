package ru.jeeconf

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.TimeUnit

@Slf4j
@RestController
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class BlockchainApplication {
  public static final int DEFAULT_PADDING = 50

  public static void main(String[] args) {
    println 'Starting'.center(DEFAULT_PADDING, '=')
    SpringApplication.run BlockchainApplication, args
    println 'Started'.center(DEFAULT_PADDING, '=')
  }

  @RequestMapping(value = '/gen', method = RequestMethod.GET)
  String gen() {
    log.info 'blockchain process'

    TimeUnit.SECONDS.sleep 3

    return UUID.randomUUID().toString()
  }
}