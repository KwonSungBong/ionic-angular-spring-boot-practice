package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "PARTICIPANT")
@IdClass(ParticipantId.class)
@Data
//@EqualsAndHashCode(exclude = {"room", "createdUser", "lastModifiedUser"})
//@ToString(exclude = {"room", "createdUser", "lastModifiedUser"})
@EqualsAndHashCode(exclude = {"user", "room"})
@ToString(exclude = {"user", "room"})
public class Participant {

    @Id
    @ManyToOne(optional=false)
    @PrimaryKeyJoinColumn(name = "USER_ID")
    private User user;

    @Id
    @ManyToOne(optional=false)
    @PrimaryKeyJoinColumn(name = "ROOM_IDX")
    private Room room;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled = true;

//    @OneToOne
//    @CreatedBy
//    private User createdUser;
//
//    @CreatedDate
//    @Column(name = "CREATED_DATE")
//    private Date createdDate;
//
//    @OneToOne
//    @LastModifiedBy
//    private User lastModifiedUser;
//
//    @LastModifiedDate
//    @Column(name = "LAST_MODIFIED_DATE")
//    private Date lastModifiedDate;

}
