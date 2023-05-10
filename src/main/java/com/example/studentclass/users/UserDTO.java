package com.example.studentclass.users;

import com.example.studentclass.shedule.SheduleDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.studentclass.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class UserDTO implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer Id;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "fio")
    private String fio;
    @Column(name = "active")
    private boolean active;
    @Column(name = "password", length = 1000)
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @Column(name = "date_of_created")
    private Date dateOfCreated;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY,
    mappedBy = "user")
    private List<SheduleDTO> visits = new ArrayList<>();

    private void init(){
        dateOfCreated = new Date();
    }
    public UserDTO(){
        init();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
