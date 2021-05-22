package com.repository;

import com.model.ReseniTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReseniTestRepository extends JpaRepository<ReseniTest, Long>
{
    List<ReseniTest> findByTestIdAndUcenikId(Long testId, Long ucenikId);
    List<ReseniTest> findByTestId(Long testId);
}
