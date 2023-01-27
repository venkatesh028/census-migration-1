package com.ideas2it.censusmigration.model;

import com.ideas2it.censusmigration.util.helper.HashMapConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
public class SourceExcelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String FileName;
    private String SheetName;
    private int rowNumber;
    @Column(columnDefinition = "json")
    @ColumnTransformer(write = "?::jsonb")
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> patientAttributes;
}
