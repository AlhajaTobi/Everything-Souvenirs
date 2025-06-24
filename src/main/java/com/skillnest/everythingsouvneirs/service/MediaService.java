package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.MediaRequest;
import com.skillnest.everythingsouvneirs.dtos.response.MediaResponse;

import java.util.List;

public interface MediaService {
    MediaResponse addMedia(MediaRequest request);

    List<MediaResponse> getAllMedia();

    MediaResponse getMediaById(String id);

    void deleteMedia(String id);
}
