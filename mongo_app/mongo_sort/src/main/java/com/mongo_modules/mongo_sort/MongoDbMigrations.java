package com.mongo_modules.mongo_sort;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Миграция для MongoDB которая инициализирует сортировку файлов по заданным путям
//ВНИМАЕНИЕ: Чтобы она сработала снова, необходимо удалить информацию о ней из базы данных
//Коллекция mongockChangeLog
@ChangeLog(order = "001")
public class MongoDbMigrations {
    @ChangeSet(order = "001", id = "files_sort", author = "ZeO")
    public void initFilesSort(ISortWorker iSortWorker, VoiceRepository voiceRepository) {
        List<Path> filesPaths = new ArrayList<>();
        //Пути к файлам относительно корня проекта
        filesPaths.add(Paths.get("mongo_app", "resources", "voice", "files", "converted"));
        filesPaths.add(Paths.get("mongo_app", "resources", "voice", "files", "src"));
        iSortWorker.sortFiles(voiceRepository.findByIsMovedIsNull(), filesPaths);
    }
}
