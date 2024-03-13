package it.syncroweb.logintest.model;

import it.syncroweb.logintest.utils.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date creationTime;

    private Date expirationTime;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public PasswordResetToken(UserEntity user, String token){
        this.user = user;
        this.creationTime = new Date();
        this.expirationTime = Utils.calculateExpiryDate();
        this.token = token;
    }

}
