package com.mongo_modules.mongo_sort;

import com.mongo_modules.mongo_sort.ent.Voice;
import com.mongo_modules.mongo_sort.repos.VoiceRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

//Основной класс выполняющий сортировку файлов
@Data
@Component
public class SortWorkerImpl implements ISortWorker {
    private final VoiceRepository voiceRepository;
    //Простой логгер для записи ошибок и информации об успешно сортированных файлах в sort_worker.log
    private static final Logger logger = LoggerFactory.getLogger(SortWorkerImpl.class);

    @Autowired
    public SortWorkerImpl(VoiceRepository voiceRepository) {
        this.voiceRepository = voiceRepository;
    }

    //Контейнер для int объектов
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class IntContainer {
        int intValue;
    }

    //Основная функция сортирвки
    @Override
    public void sortFiles(List<Voice> filesIdsList, List<Path> filesPathsList) {
        //Для каждого файла происходит поиск по его id во всех указанных путях
        filesIdsList.forEach(fL -> {
            //Счетчик путей в которых удалось найти файл
            IntContainer moveCounter = new IntContainer(0);
            filesPathsList.forEach(fP -> {
                //Поиск всех файлов название которых совпадает с id из БД (расширение не учитывается)
                try (Stream<Path> stream = Files.find(fP,
                        1,
                        (path, attr) -> path.getFileName().toString().split("\\.")[0]
                                .equals(fL.getObjectId().toString()))) {
                    //Взять первый файл
                    Optional<Path> firstFile = stream.findFirst();
                    //Если есть хотя бы один то выполнить перемещение и увеличить счетчик
                    if (firstFile.isPresent()) {
                        if (moveFile(firstFile.get(), fL.getCreatedAt()))
                            moveCounter.setIntValue(moveCounter.getIntValue() + 1);
                    }
                    //Иначе ни одного файла с таким именем найти не удалось
                    else
                        logger.error("File with id(name) " + fL.getObjectId() + " not found in path "
                                + fP);
                } catch (IOException e) {
                    //Не удалось выполнить поиск по указанному пути
                    logger.error("Can't find files in path " + fP +
                            " check if directories and files exists" + "\nError: " + e.getMessage());
                }
            });
            //Если счетчик равен размеру списка путей, значит файл успешно найден и перемещен по всем путям
            //Иначе по какому-то из путей найти или переместить файл не удалось
            if (moveCounter.getIntValue() == filesPathsList.size()) {
                fL.setIsMoved(true);
                voiceRepository.save(fL);
            }
        });
    }

    private boolean moveFile(Path fPath, LocalDateTime date) {
        //На основе полученной даты создаются дополнительные директории
        Path withDate = Paths.get(String.valueOf(date.getYear()),
                (date.getMonth().getValue() < 10 ? "0" + date.getMonth().getValue() :
                        String.valueOf(date.getMonth().getValue())),
                (date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() :
                        String.valueOf(date.getDayOfMonth())));
        //Эти директории соединяются с родительской директорией в которой находится файл,
        // тем самым формируя новое местоположение для файла
        Path finalPath = fPath.getParent().resolve(withDate);
        try {
            //Создание новых директорий в которые будет перемещен файл
            Files.createDirectories(finalPath);
            //Формирование финального пути, в который включен сам файл
            finalPath = finalPath.resolve(fPath.getFileName());
            //Перемещение файла в новую директорию
            Files.move(fPath, finalPath, REPLACE_EXISTING);
            logger.info("File " + fPath + " successfully copy to " + finalPath);
            return true;
        } catch (IOException e) {
            //Если не удалось создать новые директории или переместить файл ...
            logger.error("Can't create directories or copy file for path: " + finalPath +
                    "\nError: " + e.getMessage());
            return false;
        }
    }
}
