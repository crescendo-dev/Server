package com.insight.crescendo.repository;

import com.insight.crescendo.entity.customer_user_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends JpaRepository<customer_user_info,Long> {

}
