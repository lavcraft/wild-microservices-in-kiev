package ru.jeeconf

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.atomic.AtomicInteger

@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class RentApplication {
  public static final int DEFAULT_PADDING = 50

  public static void main(String[] args) {
    println 'Starting'.center(DEFAULT_PADDING, '=')
    SpringApplication.run RentApplication, args
    println 'Started'.center(DEFAULT_PADDING, '=')
  }

  @Bean
  PaymentClient.PaymentClientFallback clientFallback() {
    return new PaymentClient.PaymentClientFallback();
  }

  @RestController
  public static class RentController {
    private AtomicInteger hippoCount = new AtomicInteger(Integer.MAX_VALUE);

    @Autowired
    PaymentClient parrotClient
    @Autowired
    SecurityClient securityClient

    @RequestMapping(value = '/rent', method = RequestMethod.GET)
    def rent(@RequestParam Optional<Integer> count) {
      log.warn('hippo!!!')

      def hippoRequest = count.orElse(1)

      def fee = 0
      try {
        PaymentResponse feeResponse = parrotClient.getFee()
        fee = feeResponse.parrotFee
      } finally {
        securityClient.audit([fee: fee])
      }

      log.info('hippo end!!!')

      return [hippoRemain: hippoCount.getAndAdd(-1 * hippoRequest),
              parrot_fee : fee]
    }
  }
}