package com.baina.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMailRequest {
	private String name;
	private String to;
	private String from;
	private String subject;

}
