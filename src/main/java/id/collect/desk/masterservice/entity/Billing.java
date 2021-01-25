package id.collect.desk.masterservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_customer")
    @ManyToOne
    private Customer customer;

    @Column(name = "billing_amount")
    private float billingAmount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "is_close")
    private int isClose = 0;
}
