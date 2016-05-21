package ru.jeeconf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author tolkv
 * @since 20/05/16
 */
@FeignClient(value = "security-service")
public interface SecurityClient {

  @RequestMapping(value = "/audit", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Void> audit(Map params);

}
