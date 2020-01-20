package com.techieonthenet.service.impl;

import com.techieonthenet.entity.HsnCodeGstMapping;
import com.techieonthenet.repository.HsnCodeGstMappingRepository;
import com.techieonthenet.service.HsnCodeGstMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Hsn code gst mapping service.
 */
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
