package com.smartchoice.product.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
  @Autowired private BuildProperties buildProperties;

  /**
   * Get current version of service.
   *
   * @return Current version
   */
  @GetMapping("/version")
  public ResponseEntity<String> getVersion() {
    return ResponseEntity.ok(buildProperties.getVersion());
  }
}
