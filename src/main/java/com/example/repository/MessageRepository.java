package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.example.entity.Message;

public interface MessageRepository extends Repository<Message, Integer> {
    Message save(Message message);

    List<Message> findAll();

    Message findById(int id);

    // CM: JPQL because Spring can't parse this name
    @Query("SELECT msg FROM Message msg WHERE msg.posted_by = ?1")
    List<Message> findByPosted_by(int id);

    // CM: JPQL because Spring can't derive behavior for this return type otherwise
    @Modifying
    @Query("DELETE FROM Message msg WHERE msg.message_id = ?1")
    int deleteById(int id);
}
