package com.spring.kafka.avro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created on 27/August/2021 By Author Eresh, Gorantla
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String eventId;
	private String eventType;
	private long eventTimestamp;
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String city;
	private String country;
	private long createdOn;
	private long updatedOn;
}