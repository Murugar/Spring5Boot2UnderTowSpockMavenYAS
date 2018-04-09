package com.iqmsoft.weight.data;



import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;


@org.springframework.stereotype.Repository
public interface WeightDataRepository extends Repository<WeightData, Long> {
    List<WeightData> findAll();
    WeightData findByDate(LocalDate localDate);
    WeightData save(WeightData item);
}
