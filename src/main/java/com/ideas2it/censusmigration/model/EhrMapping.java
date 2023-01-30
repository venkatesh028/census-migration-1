package com.ideas2it.censusmigration.model;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EhrMapping{
    @Id
    @Column(name = "mapping_id")
    private UUID mappingId;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "source_ehr_name")
    private String sourceEhrName;
    @Column(name = "target_ehr_name")
    private String targetEhrName;
    @Column(name = "service_line")
    private String serviceLine;
    @Column(name = "target_process_name")
    private String targetProcessName;
    @Column(name = "target_sheet_name")
    private String targetSheetName;
    @Column(name = "target_field_name")
    private String targetFieldName;
    @Column(name = "target_field_type")
    private String targetFieldType;
    @Column(name = "target_field_format")
    private String targetFieldFormat;
    @Column(name = "is_target_field_mandatory")
    private String isTargetFieldMandatory;
    @Column(name = "source_file_name")
    private String sourceFileName;
    @Column(name = "source_sheet_name")
    private String sourceSheetName;
    @Column(name = "source_field_name")
    private String sourceFieldName;
    @Column(name = "source_field_type")
    private String sourceFieldType;
    @Column(name = "source_field_format")
    private String sourceFieldFormat;
    @Column(name = "created_on", updatable = false)
    @CreatedDate
    private Instant createdOn;
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    @Column(name = "modified_on", insertable = false)
    @LastModifiedDate
    private Instant modifiedOn;
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

}

