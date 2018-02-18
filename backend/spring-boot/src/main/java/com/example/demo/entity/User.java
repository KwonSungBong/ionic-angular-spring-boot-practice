package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ksb on 2017. 8. 19..
 */
@Entity
@Data
@EqualsAndHashCode(exclude = {"authorities", "socialUser"})
@ToString(exclude = {"authorities", "socialUser"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private SocialUser socialUser;

}
