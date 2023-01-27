package com.ideas2it.censusmigration.model;

import com.ideas2it.censusmigration.util.helper.HashMapConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
public class ApiLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active_user")
    private String user;

    private String url;

    private String method;

    private String requestHeaders;

    @Column(columnDefinition = "json")
    @ColumnTransformer(write = "?::jsonb")
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> requestAttachments;

    private int status;

    private String responseHeaders;

    private String responseBody;

    private LocalDateTime requestTime;
}
