package com.insight.crescendo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class customer_user_info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone_number;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,
            columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date dates_time = new Date();
    @Column(nullable = false, columnDefinition = "boolean DEFAULT false")
    private boolean checked;
}
