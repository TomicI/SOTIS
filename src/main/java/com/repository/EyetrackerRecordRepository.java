package com.repository;

import com.model.EyetrackerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EyetrackerRecordRepository extends JpaRepository<EyetrackerRecord, Long>
{
}
