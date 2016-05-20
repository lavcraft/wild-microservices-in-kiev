package ru.jeeconf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tolkv
 * @since 20/05/16
 */
@FeignClient(value = "payment-service", fallback = PaymentClient.PaymentClientFallback.class)
public interface PaymentClient {

  @RequestMapping(value = "/fee", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  PaymentResponse getFee();

  @Slf4j
  class PaymentClientFallback implements PaymentClient {

    @Override
    public PaymentResponse getFee() {
      log.warn("Use fallback");
      return new PaymentResponse(-1);
    }

  }
}
