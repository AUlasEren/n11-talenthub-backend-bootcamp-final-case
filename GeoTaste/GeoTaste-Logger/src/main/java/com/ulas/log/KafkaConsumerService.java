package com.ulas.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

  private final ErrorLogRepository errorLogRepository;

  public void processMessage(String message, String type) {
    log.info("Processing message: {}", message);

    ErrorLog errorLog = new ErrorLog();
    errorLog.setDate(LocalDateTime.now());
    errorLog.setMessage(message);
    errorLog.setDescription(type);

    errorLogRepository.save(errorLog);

    log.info("Message processed and saved: {}", message);
  }

  @KafkaListener(topics = "errorLog", groupId = "log-consumer-group")
  public void consumeError(String message) {
    processMessage(message, "Error");
  }

  @KafkaListener(topics = "infoLog", groupId = "log-consumer-group")
  public void consumeInfo(String message) {
    processMessage(message, "Info");
  }
}


