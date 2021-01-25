package id.collect.desk.masterservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_active")
    private int isActive = 1;
}
