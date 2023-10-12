package com.mongo_modules.mongo_sort.repos;

import com.mongo_modules.mongo_sort.ent.JournalRecord;

import java.util.List;

public interface JournalRepositoryCustom {
    List<JournalRecord> findIfLastUserOkLogMoreThen30Days();
}
