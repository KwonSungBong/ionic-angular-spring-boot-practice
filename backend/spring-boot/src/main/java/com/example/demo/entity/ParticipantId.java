package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParticipantId implements Serializable {

    private Long user;
    private Long room;

}
