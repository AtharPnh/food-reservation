package com.athar.food_reservation.security;

import com.athar.food_reservation.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();
    /**
     * Return the authority name as the authority
     */
    @Override
    public String getAuthority() {
        return this.name;
    }
}
