package com.skillnest.everythingsouvneirs.controller;

import com.skillnest.everythingsouvneirs.dtos.request.MediaRequest;
import com.skillnest.everythingsouvneirs.dtos.response.MediaResponse;
import com.skillnest.everythingsouvneirs.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    public ResponseEntity<MediaResponse> add(@RequestBody MediaRequest request) {
        return ResponseEntity.ok(mediaService.addMedia(request));
    }

    @GetMapping
    public ResponseEntity<List<MediaResponse>> getAll() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}
