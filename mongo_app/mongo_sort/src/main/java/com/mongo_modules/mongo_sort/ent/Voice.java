package com.mongo_modules.mongo_sort.ent;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = Voice.COLLECTION_NAME)
public class Voice {
    public static final String COLLECTION_NAME = "voice";
    @MongoId
    private ObjectId objectId;
    @Field("created_at")
    @NotNull
    private LocalDateTime createdAt;
    @Field("updated_at")
    @NotNull
    private LocalDateTime updatedAt;
    @Field("name")
    private String name;
    @Field("is_moved")
    private Boolean isMoved;
}
