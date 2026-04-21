package me.mano.electricity_billing_system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.mano.electricity_billing_system.entity.Meter;
import me.mano.electricity_billing_system.entity.MeterReading;
import me.mano.electricity_billing_system.repo.MeterReadingRepo;

@Service
public class MeterReadingService {
  
  @Autowired
  private MeterReadingRepo meterReadingRepo;

  @Autowired
  private MeterService meterService;

  public MeterReading addReading(MeterReading reading, Long meterId) {
    Meter meter = meterService.getMeterById(meterId);
    reading.setMeter(meter);
    if(reading.getReadingDate() == null) {
        reading.setReadingDate(LocalDate.now());
    }
    return meterReadingRepo.save(reading);
  }

  public List<MeterReading> getReadingsByMeterId(Long meterId) {
    return meterReadingRepo.findByMeterId(meterId);
  }
}
