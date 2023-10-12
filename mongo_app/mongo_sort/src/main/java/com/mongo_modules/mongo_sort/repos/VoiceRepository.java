package com.mongo_modules.mongo_sort.repos;

import com.mongo_modules.mongo_sort.ent.Voice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoiceRepository extends MongoRepository<Voice, ObjectId> {
    List<Voice> findByIsMovedIsNull();
}
