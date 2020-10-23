package cz.intv.lundegaard.demo.repository;

import cz.intv.lundegaard.demo.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, String> {
}
