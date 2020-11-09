package com.hp.warriors.service;

import com.hp.warriors.entity.Province;
import com.hp.warriors.mapper.warriors.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    public int save(Province province) {
        if (Objects.isNull(province.getId())) {
            return provinceMapper.insert(province);
        }
        return provinceMapper.update(province);
    }

    public Province get(Integer id) {
        return provinceMapper.get(id);
    }
}
