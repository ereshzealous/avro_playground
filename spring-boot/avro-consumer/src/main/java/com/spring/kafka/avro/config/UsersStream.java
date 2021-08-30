package com.spring.kafka.avro.config;

import com.spring.kafka.avro.user.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Created on 27/August/2021 By Author Eresh, Gorantla
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class UsersStream {

	@Bean
	public Consumer<Message<UserEvent>> users() {
		return message -> {
			log.info("\n---\nHeaders: {}\n\nPayload: {}\n---", message.getHeaders(), message.getPayload());
		};
	}

}