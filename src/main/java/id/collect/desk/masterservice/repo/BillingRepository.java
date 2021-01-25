package id.collect.desk.masterservice.repo;

import id.collect.desk.masterservice.entity.Billing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends CrudRepository<Billing, Long> {

    @Query(value = "select cs.name, cs.address, cs.phone_number, bl.billing_amount, bl.payment_date, bl.is_close " +
            "from billing bl inner join customer cs ON cs.id = bl.id_customer where bl.is_close = :isClose", nativeQuery = true)
    List<Object[]> listByStatus(@Param("isClose") int isClose);
}
