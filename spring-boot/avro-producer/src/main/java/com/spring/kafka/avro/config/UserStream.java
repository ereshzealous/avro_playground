package com.spring.kafka.avro.config;

import com.spring.kafka.avro.dto.UserDTO;
import com.spring.kafka.avro.user.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

/**
 * Created on 27/August/2021 By Author Eresh, Gorantla
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class UserStream {

	private final StreamBridge streamBridge;

	@Value("${spring.cloud.stream.bindings.users-out-0.content-type}")
	private String streamOutMimeType;

	@Scheduled(fixedDelay = 15000)
	public void publishUserData() {
		UserEvent userDTO = new UserEvent();
		userDTO.setCity(RandomStringUtils.randomAlphabetic(10));
		userDTO.setCountry("India");
		userDTO.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");
		userDTO.setEventId(UUID.randomUUID().toString());
		userDTO.setEventTimestamp(Instant.now().getEpochSecond());
		userDTO.setId(UUID.randomUUID().toString());
		userDTO.setEventType("CREATED");
		userDTO.setFirstName(RandomStringUtils.randomAlphabetic(10));
		userDTO.setLastName(RandomStringUtils.randomAlphabetic(10));
		userDTO.setMobileNumber(RandomStringUtils.randomNumeric(10));
		userDTO.setCreatedOn(Instant.now().getEpochSecond());
		userDTO.setUpdatedOn(Instant.now().getEpochSecond());
		Random random = new Random();
		userDTO.setActive(random.nextBoolean());
		Message<UserEvent> message = MessageBuilder.withPayload(userDTO).build();
		streamBridge.send("users-out-0", message, MimeType.valueOf(streamOutMimeType));
	}

}