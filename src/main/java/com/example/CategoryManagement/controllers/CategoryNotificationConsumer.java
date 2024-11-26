package com.example.CategoryManagement.controllers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CategoryNotificationConsumer {

    @KafkaListener(topics = "category-notifications", groupId = "category-group")
    public void consumeCategoryNotification(ConsumerRecord<String, String> record) {
        String message = record.value();
        System.out.println("Received Notification: " + message);

    }

    @KafkaListener(topics = "category-data-changes", groupId = "category-group")
    public void consumeCategoryDataChange(ConsumerRecord<String, String> record) {
        String categoryMessage = record.value();
        System.out.println("Received Category Data Change: " + categoryMessage);
    }
}
