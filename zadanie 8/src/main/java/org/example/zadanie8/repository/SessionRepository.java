package org.example.zadanie8.repository;

import org.example.zadanie8.entity.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Integer> {

    @Query("SELECT s FROM Session s WHERE s.uuid = :uuid")
    List<Session> findByUuid(@Param("uuid") String uuid);
}
