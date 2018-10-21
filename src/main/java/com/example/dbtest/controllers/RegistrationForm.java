package com.example.dbtest.controllers;

import com.example.dbtest.validator.UserInfoExists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

	@NotNull(message = "空白は不可")
	@Size(min = 1, message = "文字を入力してください")
	private String username;
	
	@NotNull(message = "空白は不可")
	@Size(min = 1, message = "文字を入力してください")
	@Email(message="メールアドレスを入力してください")
	@UserInfoExists(message="そのメールはすでに存在します")
	private String email;
	
	@NotNull(message = "空白は不可")
	@Size(min = 1, message = "文字を入力してください。")
	private String password;
	
	//@NotNull(message = "is required")
	//@Size(min = 1, message = "is required")
	private Boolean enabled;
	
	//@NotNull(message = "is required")
	//@Size(min = 1, message = "is required")
	private String authority;
	
	public boolean isNewRegistration;
	

}
