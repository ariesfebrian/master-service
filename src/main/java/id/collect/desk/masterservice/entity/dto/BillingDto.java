package id.collect.desk.masterservice.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BillingDto {

    private Long id;
    private Long idCustomer;
    private float billingAmount;
    private Date paymentDate;
    private int isClose;
}
