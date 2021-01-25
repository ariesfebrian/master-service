package id.collect.desk.masterservice.repo;

import id.collect.desk.masterservice.entity.Collector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectorRepository extends CrudRepository<Collector, Long> {
}
