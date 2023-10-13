package com.mongo_modules.mongo_sort.repos.impl;

import com.mongo_modules.mongo_sort.ent.JournalRecord;
import com.mongo_modules.mongo_sort.repos.JournalRepositoryCustom;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Repository
public class JournalRepositoryCustomImpl implements JournalRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public JournalRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<JournalRecord> findIfLastUserOkLogMoreThen30Days() {
        LocalDateTime timeNowMinus30Days = LocalDateTime.now().minus(Period.ofDays(30));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(new Criteria().andOperator(
                        Criteria.where("result").is("OK"),
                        Criteria.where("event").is("LOGIN")
                )),
                Aggregation.lookup("users", "user._id", "_id", "join_user"),
                Aggregation.match(Criteria.where("join_user.0").exists(true)),
                Aggregation.sort(Sort.Direction.DESC, "dateTime"),
                Aggregation.group("user._id")
                        .first("$$ROOT").as("record"),
                Aggregation.match(Criteria.where("record.dateTime").lt(timeNowMinus30Days))
        );
        AggregationResults<Document> results = mongoTemplate.aggregate(
                aggregation, "journal", Document.class);
        return results.getMappedResults().stream()
                .map(doc -> mongoTemplate.getConverter()
                        .read(JournalRecord.class, doc.get("record", Document.class))).toList();
    }
}
