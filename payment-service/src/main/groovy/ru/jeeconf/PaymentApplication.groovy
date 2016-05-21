package ru.jeeconf

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.ThreadLocalRandom

@Slf4j
@RestController
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class PaymentApplication {
  public static final int DEFAULT_PADDING = 50

  public static void main(String[] args) {
    println 'Starting'.center(DEFAULT_PADDING, '=')
    SpringApplication.run PaymentApplication, args
    println 'Started'.center(DEFAULT_PADDING, '=')
  }

  @Autowired
  BlockchainClient blockchainClient
  @Autowired
  SecurityClient securityClient

  @RequestMapping(value = '/fee', method = RequestMethod.GET)
  def fee() {
    def fee
    try {
      log.warn 'payment process'
      fee = ThreadLocalRandom.current().nextInt(100)
      return [
          fee : fee,
          hash: blockchainClient.gen()
      ]
    } finally {
      securityClient.audit([fee: fee])
    }
  }
}