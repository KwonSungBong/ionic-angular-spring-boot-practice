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

/**
 * Created by whilemouse on 17. 10. 13.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MESSAGE")
@Data
@EqualsAndHashCode(exclude = {"room", "createdUser", "lastModifiedUser"})
@ToString(exclude = {"room", "createdUser", "lastModifiedUser"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private long idx;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_IDX")
    private Room room;

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
