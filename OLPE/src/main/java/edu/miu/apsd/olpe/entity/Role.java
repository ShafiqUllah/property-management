package edu.miu.apsd.olpe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    String role;
    String description;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<User> users;
}
