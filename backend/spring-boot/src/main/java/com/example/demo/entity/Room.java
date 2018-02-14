package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by whilemouse on 17. 10. 13.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ROOM")
@Data
@EqualsAndHashCode(exclude = {"participantList", "messageList", "createdUser", "lastModifiedUser"})
@ToString(exclude = {"participantList", "messageList", "createdUser", "lastModifiedUser"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private long idx;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PARTICIPANT_LIMIT")
    private int participantLimit;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Participant> participantList;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messageList;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled = true;

    @OneToOne
    @CreatedBy
    private User createdUser;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @OneToOne
    @LastModifiedBy
    private User lastModifiedUser;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

}
