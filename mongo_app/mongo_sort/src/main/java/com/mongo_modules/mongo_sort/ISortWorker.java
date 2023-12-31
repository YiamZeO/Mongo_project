package com.mongo_modules.mongo_sort;

import com.mongo_modules.mongo_sort.ent.Voice;

import java.nio.file.Path;
import java.util.List;

public interface ISortWorker {
    public void sortFiles(List<Voice> filesIdsList, List<Path> filesPathsList);
}
