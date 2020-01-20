package com.techieonthenet.service.impl;

import com.techieonthenet.entity.HsnCodeGstMapping;
import com.techieonthenet.repository.HsnCodeGstMappingRepository;
import com.techieonthenet.service.HsnCodeGstMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HsnCodeGstMappingServiceImpl implements HsnCodeGstMappingService {

    @Autowired
    private HsnCodeGstMappingRepository hsnCodeGstMappingRepository;

    @Override
    public Iterable<HsnCodeGstMapping> findAll() {
        return hsnCodeGstMappingRepository.findAll();
    }

    @Override
    public HsnCodeGstMapping findByHsnCode(int hsnCode) {
        return hsnCodeGstMappingRepository.findByHsnCode(hsnCode);
    }
}
