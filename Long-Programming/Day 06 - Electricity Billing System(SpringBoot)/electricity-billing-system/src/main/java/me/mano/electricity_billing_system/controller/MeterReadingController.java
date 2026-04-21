package me.mano.electricity_billing_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.mano.electricity_billing_system.entity.MeterReading;
import me.mano.electricity_billing_system.service.MeterReadingService;

@RestController
@RequestMapping("/meter-readings")
public class MeterReadingController {
  
  @Autowired
  private MeterReadingService meterReadingService;

  @PostMapping("/meter/{meterId}")
  public MeterReading addReading(@RequestBody MeterReading reading, @PathVariable Long meterId) {
    return meterReadingService.addReading(reading, meterId);
  }

  @GetMapping("/meter/{meterId}")
  public List<MeterReading> getReadingsByMeterId(@PathVariable Long meterId) {
    return meterReadingService.getReadingsByMeterId(meterId);
  }
}

/*
  /meter/{meterId} - POST - Add a new meter reading for a specific meter
    {
    "readingDate": "2026-04-30",
    "readingValue": 150.0
  }

  /meter/{meterId} - GET - Get all meter readings for a specific meter

*/
