package com.repository;

import com.model.Regioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegioniRepository extends JpaRepository<Regioni, Long>
{
}
