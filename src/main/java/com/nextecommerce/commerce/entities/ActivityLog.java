package com.nextecommerce.commerce.entities;

import com.nextecommerce.commerce.enums.ActivityLogStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

@Slf4j
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_log")
public class ActivityLog extends BaseEntity implements Serializable {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityLogStatus status;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description", length = 500)
    private String description;


    @Column(name = "source", length = 500)
    private String source;

    @Column(name = "ip_address", length = 25)
    private String ipAddress;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "json")
    private String payload;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "result", columnDefinition = "json")
    private String result;

}
