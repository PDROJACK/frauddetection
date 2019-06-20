package com.airtel.frauddetection;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtel.frauddetection.DataModel;

@Repository
public interface DataRepository extends JpaRepository<DataModel, Long>{}