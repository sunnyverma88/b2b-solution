package com.techieonthenet.service;

import com.techieonthenet.entity.HsnCodeGstMapping;

public interface HsnCodeGstMappingService {

    Iterable<HsnCodeGstMapping> findAll();

    HsnCodeGstMapping findByHsnCode(int hsnCode);
}
