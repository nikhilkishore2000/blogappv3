package com.blogapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	
	private String email;
	
	private String accessToken;
	
	
}
