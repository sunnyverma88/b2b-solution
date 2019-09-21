package com.techieonthenet.repository;

import com.techieonthenet.entity.HsnCodeGstMapping;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HsnCodeGstMappingRepository extends PagingAndSortingRepository<HsnCodeGstMapping, Long> {

    HsnCodeGstMapping findByHsnCode(int hsnCode);
}
