package com.mongo_modules.mongo_sort.ent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = User.COLLECTION_NAME)
@EqualsAndHashCode
public class User {
    public static final String COLLECTION_NAME = "users";

    @Id
    private Long id;
    //    @Indexed(/*unique = true,*/ direction = IndexDirection.DESCENDING, name = "users_login")
    private String login;
    @JsonIgnore
    private String password;
    private String description;
    private String email;
}

