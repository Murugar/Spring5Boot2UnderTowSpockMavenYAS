package com.iqmsoft.weight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iqmsoft.weight.WeightReader;
import com.iqmsoft.weight.data.WeightData;
import com.iqmsoft.weight.data.WeightDataRepository;

import java.time.LocalDate;
import java.util.List;


@Service
public class WeightService {


    private WeightDataRepository weightDataRepository;

    @Autowired
    public WeightService(WeightDataRepository dataRepository) {
        Assert.notNull(dataRepository, "Repository should not be null");
        this.weightDataRepository = dataRepository;
    }

    public int numberOfLines() {
        return  weightDataRepository.findAll().size();
    }

    public  Double getAvg() {
        return weightDataRepository.findAll().stream().mapToDouble(WeightData::getWeight).average().orElse(Double.NaN);
    }

    public  float getWeightFromDate(LocalDate localDate) {
        WeightData weightData = weightDataRepository.findByDate(localDate);
        return  weightData.getWeight();
    }

    public List<WeightData> getDataSets() {
        return  weightDataRepository.findAll();
    }
}
