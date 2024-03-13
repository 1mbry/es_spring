package it.syncroweb.logintest.model;

import it.syncroweb.logintest.utils.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_token")
public class EmailToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationTime;

    private Date expirationTime;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_VERIFY_USER"))
    private UserEntity user;

    public EmailToken(UserEntity user, String token) {
        this.user = user;
        this.creationTime = new Date();
        this.expirationTime = Utils.calculateExpiryDate();
        this.token = token;
    }
}
