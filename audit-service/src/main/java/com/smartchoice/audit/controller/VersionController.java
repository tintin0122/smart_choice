package com.smartchoice.audit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

  /**
   * Get current version of service.
   *
   * @return Current version
   */
  @GetMapping("/version")
  public ResponseEntity<String> getVersion() {
    return ResponseEntity.ok("1.0.0");
  }
}
