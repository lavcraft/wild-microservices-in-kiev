package ru.joker

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.time.ZonedDateTime

@Slf4j
@RestController
@SpringBootApplication
public class FbiApplication {
  public static final int DEFAULT_PADDING = 50

  public static void main(String[] args) {
    println 'Starting'.center(DEFAULT_PADDING, '=')
    SpringApplication.run FbiApplication, args
    println 'Started'.center(DEFAULT_PADDING, '=')
  }

  @RequestMapping(value = '/fink', method = RequestMethod.POST)
  def fink(@RequestBody Map<String, String> request) {
    println "fink : $request"
    return [lastFinkDate: ZonedDateTime.now()]
  }
}