package com.example.rabbitmq.producer.stream.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private int amount;
    private Status status;
    private String invoiceNumber;
    public enum Status {
        CREATED, APPROVED, PAID, REJECTED
    }

}
