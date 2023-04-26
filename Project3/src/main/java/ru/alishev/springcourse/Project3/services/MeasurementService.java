package ru.alishev.springcourse.Project3.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project3.models.Measurement;
import ru.alishev.springcourse.Project3.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }
    @Transactional
    public void save(Measurement measurement){
        enrich(measurement);
        measurementRepository.save(measurement);
    }

    private void enrich(Measurement measurement){
        measurement.setChangedAt(LocalDateTime.now());
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
    }
}
