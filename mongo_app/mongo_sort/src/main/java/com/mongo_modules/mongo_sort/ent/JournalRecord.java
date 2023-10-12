package com.mongo_modules.mongo_sort.ent;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = JournalRecord.COLLECTION_NAME)
public class JournalRecord {

    public static final String COLLECTION_NAME = "journal";
    @Id
    private Long id;
    private LocalDateTime dateTime;
    private User user;
    @NotNull
    private EventType event;
    private String message;
    private Object details;
    private String result;
}

