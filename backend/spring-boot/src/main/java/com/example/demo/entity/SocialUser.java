package com.example.demo.entity;

import com.example.demo.entity.constant.SocialProvider;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
public class SocialUser implements Serializable {

    @Id
    @OneToOne
    @NotNull
    @JoinColumn(name = "ID")
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SocialProvider providerId;

    private String providerUserId;

    private int rank;

    private String email;

    private String displayNm;

    private String profileUrl;

    private String imageUrl;

    private String accessToken;

    private String secret;

    private String refreshToken;

    private long expireTime;

    private String displayName;
}
