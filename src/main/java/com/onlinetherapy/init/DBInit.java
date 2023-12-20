package com.onlinetherapy.init;

import com.onlinetherapy.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

  private final InitService initService;

  public DBInit(InitService initService) {
    this.initService = initService;
  }

  @Override
  public void run(String... args) {
    initService.init();
  }
}
