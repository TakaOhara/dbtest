package com.example.dbtest.repositories;

import com.example.dbtest.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
	
	UserInfo findByEmail(String email);

}
