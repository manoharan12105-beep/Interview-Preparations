package me.mano.electricity_billing_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.mano.electricity_billing_system.entity.Meter;
import me.mano.electricity_billing_system.entity.User;
import me.mano.electricity_billing_system.repo.MeterRepo;

@Service
public class MeterService {
  
  @Autowired
  private MeterRepo meterRepo;

  @Autowired
  private UserService userService;

  public Meter createMeter(Meter meter, Long userId) {
    User user = userService.getUserById(userId);
    meter.setUser(user);
    if(meter.getStatus() == null) {
        meter.setStatus("Active");
    }
    return meterRepo.save(meter);
  }

  public List<Meter> getMetersByUserId(Long userId) {
    return meterRepo.findByUserId(userId);
  }

  public Meter getMeterById(Long id) {
    return meterRepo.findById(id).orElseThrow(() -> new RuntimeException("Meter not found"));
  }

  public Meter updateMeterStatus(Long id, String status) {
    Meter meter = getMeterById(id);
    meter.setStatus(status);
    return meterRepo.save(meter);
  }
}
