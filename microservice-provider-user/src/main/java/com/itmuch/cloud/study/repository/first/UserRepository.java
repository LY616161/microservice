package com.itmuch.cloud.study.repository.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itmuch.cloud.study.entity.first.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
