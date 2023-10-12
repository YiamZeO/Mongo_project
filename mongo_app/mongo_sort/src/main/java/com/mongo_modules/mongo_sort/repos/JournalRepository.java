package com.mongo_modules.mongo_sort.repos;

import com.mongo_modules.mongo_sort.ent.JournalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalRecord, String>, JournalRepositoryCustom {
}
