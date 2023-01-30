package com.ideas2it.censusmigration.model;

import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TargetMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceEhrName;
    private String serviceLine;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, Object> patientAttributes;
    @CreatedDate
    @Column(updatable = false)
    private Timestamp createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private Timestamp modifiedAt;
    @CreatedBy
    private String createBy;
    @LastModifiedBy
    private String modifiedBy;
}
