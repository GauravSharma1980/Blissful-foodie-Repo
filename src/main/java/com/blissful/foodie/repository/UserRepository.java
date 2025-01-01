package com.blissful.foodie.repository;

import com.blissful.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
