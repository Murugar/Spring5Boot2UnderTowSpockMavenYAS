package com.iqmsoft.weight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.iqmsoft.weight.data.WeightData;
import com.iqmsoft.weight.data.WeightDataRepository;

import java.io.IOException;
import java.util.List;


@Component
public class WeightDataImporter {

    private Logger logger = LoggerFactory.getLogger(WeightDataImporter.class);

    private WeightDataRepository weightDataRepository;

    private WeightReader weightReader;

    @Autowired
    public WeightDataImporter(WeightDataRepository repository, WeightReader weightReader) {
        Assert.notNull(repository, "Repository should not be null");
        this.weightDataRepository = repository;
        this.weightReader = weightReader;
        importData();
    }


    private void importData()  {

        logger.info("Start Import");

        try {
            weightReader.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<WeightData> weightDatas = weightReader.getWeightDatas();

        weightDatas.stream().forEach(weightData -> weightDataRepository.save(weightData));

        logger.info("Imported " + weightDataRepository.findAll().size() + " Datasets");

    }
}
