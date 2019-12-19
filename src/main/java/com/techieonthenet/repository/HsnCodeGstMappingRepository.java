package com.techieonthenet.repository;

import com.techieonthenet.entity.HsnCodeGstMapping;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Hsn code gst mapping repository.
 */
@Repository
public interface HsnCodeGstMappingRepository extends PagingAndSortingRepository<HsnCodeGstMapping, Long> {

    /**
     * Find by hsn code hsn code gst mapping.
     *
     * @param hsnCode the hsn code
     * @return the hsn code gst mapping
     */
    HsnCodeGstMapping findByHsnCode(int hsnCode);
}
