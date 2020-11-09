package com.hp.warriors.service;

import com.hp.warriors.entity.City;
import com.hp.warriors.mapper.warriors.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public int save(City city) {
        if (Objects.isNull(city.getId())) {
            return cityMapper.insert(city);
        }
        return cityMapper.update(city);

    }

    public City get(Integer id) {
        return cityMapper.get(id);
    }

    public List<City> listByProvinceCode(String provinceCode) {
        return cityMapper.listByProvinceCode(provinceCode);
    }

}
