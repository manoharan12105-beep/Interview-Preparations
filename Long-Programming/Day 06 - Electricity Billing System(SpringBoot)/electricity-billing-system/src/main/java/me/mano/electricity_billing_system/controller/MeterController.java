package me.mano.electricity_billing_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.mano.electricity_billing_system.entity.Meter;
import me.mano.electricity_billing_system.service.MeterService;

@RestController
@RequestMapping("/meters")
public class MeterController {
  
  @Autowired
  private MeterService meterService;

  @PostMapping("/user/{userId}")
  public Meter createMeter(@RequestBody Meter meter, @PathVariable Long userId) {
    return meterService.createMeter(meter, userId);
  }

  @GetMapping("/user/{userId}")
  public List<Meter> getMetersByUserId(@PathVariable Long userId) {
    return meterService.getMetersByUserId(userId);
  }

  @PutMapping("/{id}/status")
  public Meter updateMeterStatus(@PathVariable Long id, @RequestParam String status) {
    return meterService.updateMeterStatus(id, status);
  }
}

/*

  /user/{userId} - POST - Create a new meter for a specific user
    {
    "meterNumber": "MTR123456",
    "installationDate": "2026-04-21",
    "location": "11/43, south street, anna nagar, madurai-96"
  }

  /user/{userId} - GET - Get all meters for a specific user

  /{id}/status - PUT - Update the status of a specific meter
    {
    "status": "Active"
  }

*/
