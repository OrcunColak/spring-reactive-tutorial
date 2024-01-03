package com.colak.springreactivetutorial.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@RestController
@RequestMapping("/v1/filestorage")
public class FileStorageController {

    private final Path basePath = Paths.get("uploads");

    @PostMapping("/upload")
    public Mono<ResponseEntity<Object>> upload(@RequestPart("files") Flux<FilePart> partFlux) {
        Flux<ResponseEntity<Object>> responseEntityFlux = partFlux
                .flatMap(this::transferTo);
        return Mono.from(responseEntityFlux);

    }

    private Mono<ResponseEntity<Object>> transferTo(FilePart filePart) {
        try {
            Path resolved = basePath.resolve(filePart.filename());
            log.info("Full path : {}", resolved.toAbsolutePath());

            truncateFile(resolved);

            filePart.transferTo(resolved);
            return Mono.just(ResponseEntity.ok().build());
        } catch (IOException ioException) {
            log.error("Exception caught", ioException);
            return Mono.just(ResponseEntity.badRequest().body(ioException));
        }
    }

    private void truncateFile(Path resolved) throws IOException {
        Files.createDirectories(basePath);
        Files.newOutputStream(resolved, StandardOpenOption.TRUNCATE_EXISTING).close();
    }

}
