package com.itmuch.cloud.study.repository.second;

import com.itmuch.cloud.study.entity.second.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


}
