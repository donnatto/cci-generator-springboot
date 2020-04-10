package com.donnatto.ccigenerator.repository;

import com.donnatto.ccigenerator.model.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {

    Office findByOldnumber(String oldNumber);
}
