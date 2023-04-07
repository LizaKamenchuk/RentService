package com.kamenchuk.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamenchuk.dto.PhotoDto;
import com.kamenchuk.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KafkaPhotoConsumer {

    private final ObjectMapper objectMapper;
    private final PhotoService photoService;

    @Autowired
    public KafkaPhotoConsumer(ObjectMapper objectMapper, PhotoService photoService) {
        this.objectMapper = objectMapper;
        this.photoService = photoService;
    }

    @KafkaListener(topics = "save", groupId = "myGroup")
    public void listenToSetPhotoTopic(String photo) throws IOException {
        PhotoDto dto = objectMapper.readValue(photo,PhotoDto.class);
        photoService.addPhoto(dto);
        log.info("consumer");
    }
}
