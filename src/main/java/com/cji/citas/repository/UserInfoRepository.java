package com.cji.citas.repository;

import com.cji.citas.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

import java.util.Optional; 

@Repository
public interface UserInfoRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByName(String username);
}
