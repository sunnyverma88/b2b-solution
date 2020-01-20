package com.techieonthenet.service;

import com.techieonthenet.entity.HsnCodeGstMapping;

/**
 * The interface Hsn code gst mapping service.
 */
public interface HsnCodeGstMappingService {

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<HsnCodeGstMapping> findAll();

    /**
     * Find by hsn code hsn code gst mapping.
     *
     * @param hsnCode the hsn code
     * @return the hsn code gst mapping
     */
    HsnCodeGstMapping findByHsnCode(int hsnCode);
}
