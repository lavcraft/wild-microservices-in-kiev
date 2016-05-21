package ru.jeeconf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tolkv
 * @since 20/05/16
 */
@FeignClient(value = "blockchain-service")
public interface BlockchainClient {

  @RequestMapping(value = "/gen", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  String gen();

}
