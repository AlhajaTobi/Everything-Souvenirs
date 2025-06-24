package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.data.model.Media;
import com.skillnest.everythingsouvneirs.data.repository.MediaRepository;
import com.skillnest.everythingsouvneirs.dtos.request.MediaRequest;
import com.skillnest.everythingsouvneirs.dtos.response.MediaResponse;
import com.skillnest.everythingsouvneirs.exception.MediaNotFoundException;
import com.skillnest.everythingsouvneirs.mapper.MediaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    public MediaResponse addMedia(MediaRequest request) {
        Media media = MediaMapper.mapToMedia(request);
        Media savedMedia = mediaRepository.save(media);
        return MediaMapper.mapToMediaResponse(savedMedia);
    }

    @Override
    public List<MediaResponse> getAllMedia() {
        return mediaRepository.findAll().stream()
                .map(MediaMapper::mapToMediaResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MediaResponse getMediaById(String id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new MediaNotFoundException("Media not found"));
        return MediaMapper.mapToMediaResponse(media);
    }

    @Override
    public void deleteMedia(String id) {
        if (!mediaRepository.existsById(id)) {
            throw new MediaNotFoundException("Media not found");
        }
        mediaRepository.deleteById(id);
    }
}
