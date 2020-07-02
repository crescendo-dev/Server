package com.insight.crescendo.repository;

import com.insight.crescendo.entity.account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountRepostitory extends JpaRepository<account,Long> {

}
