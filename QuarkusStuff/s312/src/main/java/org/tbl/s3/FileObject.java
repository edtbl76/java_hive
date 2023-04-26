package org.tbl.s3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.model.S3Object;

@NoArgsConstructor
public class FileObject {

    @Getter
    private String objectKey;
    @Getter
    private Long size;

    public static FileObject from(S3Object s3Object) {
        FileObject file = new FileObject();
        if (s3Object != null) {
            file.setObjectKey(s3Object.key());
            file.setSize(s3Object.size());
        }
        return file;
    }

    public FileObject setObjectKey(String objectKey) {
        this.objectKey = objectKey;
        return this;
    }

    public FileObject setSize(Long size) {
        this.size = size;
        return this;
    }
}
