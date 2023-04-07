package org.kamenchuk.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaCarProducer {
    private final KafkaTemplate<String, String > kafkaTemplate;
    static final String TOPIC_SAVE_PHOTO = "save";
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaCarProducer(KafkaTemplate<String, String> kafkaTemplate,
                            ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper=objectMapper;
    }
    public void sendGetPhotoTopic(PhotoDto dto) {
        System.out.println(dto.getFileName());
        String photo = "";
        try { photo = objectMapper.writeValueAsString(dto);
        } catch (Exception e){
            e.printStackTrace();
        }
        log.info(String.format("#### -&gt; Producing message to send photos -&gt; %s", photo));
        kafkaTemplate.send(TOPIC_SAVE_PHOTO, photo);
    }


//    @Autowired
//    public KafkaCarProducer(KafkaTemplate<String,MultipartFile> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    public void sendGetPhotoTopic(MultipartFile photo) {
//        log.info(String.format(" Producing message to send photos  %s", photo));
//        kafkaTemplate.send(TOPIC_SAVE_PHOTO, photo);
//        log.info("Message send to kafka");
//    }
}
