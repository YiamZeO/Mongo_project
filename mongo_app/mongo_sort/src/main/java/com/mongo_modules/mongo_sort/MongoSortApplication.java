package com.mongo_modules.mongo_sort;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongock
public class MongoSortApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoSortApplication.class, args);
    }

}
