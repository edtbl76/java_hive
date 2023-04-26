package com.example.uploadingfiles.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

public class FileSystemStorageServiceTests {

    private final StorageProperties storageProperties = new StorageProperties();
    private FileSystemStorageService fileSystemStorageService;

    @BeforeEach
    public void init() {
        storageProperties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
        fileSystemStorageService = new FileSystemStorageService(storageProperties);
        fileSystemStorageService.init();
    }

    @Test
    public void loadNonExistent() {
        assertThat(fileSystemStorageService.load("foo.txt")).doesNotExist();
    }

    @Test
    public void saveAndLoad() {
        fileSystemStorageService.store(
                new MockMultipartFile("foo", "foo.txt",
                        TEXT_PLAIN_VALUE, "Hello World".getBytes(UTF_8))
        );
    }

    @Test
    public void saveRelativePathNotPermitted() {
        assertThrows(StorageException.class, () -> fileSystemStorageService.store(
                new MockMultipartFile("foo", "../foo.txt",
                        TEXT_PLAIN_VALUE, "Hello World".getBytes(UTF_8))
        ));
    }

    @Test
    public void saveAbsolutePathNotPermitted() {
        assertThrows(StorageException.class, () -> fileSystemStorageService.store(
                new MockMultipartFile("foo", "/etc/passwd",
                        TEXT_PLAIN_VALUE, "Hello World".getBytes(UTF_8))
        ));
    }

    @Test
    @EnabledOnOs({OS.MAC})
    public void saveAbsolutePathInFilenamePermitted() {
        String fileName = "\\etc\\passwd";
        fileSystemStorageService.store(
                new MockMultipartFile(fileName, fileName,TEXT_PLAIN_VALUE, "Hello World".getBytes(UTF_8)));

        assertTrue(Files.exists(
                Paths.get(storageProperties.getLocation())
                        .resolve(Paths.get(fileName))));
    }

    @Test
    public void savePermitted() {
        fileSystemStorageService.store(
                new MockMultipartFile("foo", "bar/../foo.txt",
                        TEXT_PLAIN_VALUE, "Hello World".getBytes(UTF_8)));
    }
}
