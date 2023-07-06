package com.md.ff.repository;

import com.md.ff.entity.Price;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID>,
    JpaSpecificationExecutor<Price> {


}
