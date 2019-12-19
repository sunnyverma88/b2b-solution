package com.techieonthenet.repository.amazon.s3;

import com.techieonthenet.repository.FileRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * The type Amazon s 3.
 */
public class AmazonS3 implements FileRepository {

    private Region defaultRegion = Region.US_EAST_1;
    private Region region = defaultRegion;
    private S3Client s3 = S3Client.builder().region(defaultRegion).build();

    private void createBucket(String clientId, Region region) {
        // Create bucket
        CreateBucketRequest createBucketRequest = CreateBucketRequest
                .builder()
                .bucket(bucketName(clientId))
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(region.id())
                        .build())
                .build();
        s3.createBucket(createBucketRequest);
    }

    private Optional<Bucket> getBucket(String clientId) {
        ListBucketsRequest request = ListBucketsRequest.builder().build();
        ListBucketsResponse response = s3.listBuckets(request);
        return response.buckets().stream().filter((x) -> x.name().
                equalsIgnoreCase(bucketName(clientId))).findFirst();
    }

    private String bucketName(String clientId) {
        return "bucket-" + clientId;
    }

    @Override
    public void storeFile(String clientId, String type, byte[] bytes) {
        Optional<Bucket> bucket = getBucket(clientId);

        if (!bucket.isPresent()) {
            createBucket(clientId, region);
        }

        String key = type + "-" + System.currentTimeMillis();

        s3.putObject(PutObjectRequest.builder().bucket(bucketName(clientId)).key(key)
                        .build(),
                RequestBody.fromByteBuffer(ByteBuffer.wrap(bytes)));

    }
}