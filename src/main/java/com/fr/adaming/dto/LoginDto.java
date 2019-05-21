package com.fr.adaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@ NoArgsConstructor
public class LoginDto {
		
	@Email
	@NotNull
	@NotBlank
		private String email;
	@NotNull
	@NotBlank
		private String pwd;

}
