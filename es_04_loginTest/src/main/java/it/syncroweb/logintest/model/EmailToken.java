package it.syncroweb.logintest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_token")
public class EmailToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationTime;

    private Date expirationTime;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public EmailToken(UserEntity user, String token) {
        this.user = user;
        this.creationTime = new Date();
        this.expirationTime = calculateExpiryDate();
        this.token = token;
    }

    private Date calculateExpiryDate() {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, EmailToken.EXPIRATION);
        return new Date(cal.getTime().getTime());
    }
}
