package com.repository;

import com.model.AnalizaPogleda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalizaPogledaRepository extends JpaRepository<AnalizaPogleda, Long>
{
    List<AnalizaPogleda> findByReseniTestId(Long reseniTestId);
}
