package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.Media;
import com.skillnest.everythingsouvneirs.dtos.request.MediaRequest;
import com.skillnest.everythingsouvneirs.dtos.response.MediaResponse;

public class MediaMapper {

    public static Media mapToMedia(MediaRequest request) {
        Media media = new Media();
        media.setUrl(request.getUrl());
        media.setType(request.getType());
        return media;
    }

    public static MediaResponse mapToMediaResponse(Media media) {
        return MediaResponse.builder()
                .id(media.getId())
                .url(media.getUrl())
                .type(media.getType())
                .build();
    }
}
