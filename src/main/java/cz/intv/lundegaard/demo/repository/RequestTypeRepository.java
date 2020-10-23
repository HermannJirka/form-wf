package cz.intv.lundegaard.demo.repository;

import cz.intv.lundegaard.demo.model.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, String> {
}
