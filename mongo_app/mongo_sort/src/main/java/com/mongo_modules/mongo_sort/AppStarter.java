package com.mongo_modules.mongo_sort;

import com.mongo_modules.mongo_sort.ent.JournalRecord;
import com.mongo_modules.mongo_sort.repos.JournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppStarter {
    private final JournalRepository journalRepository;

    @Autowired
    public AppStarter(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @PostConstruct
    public void postConst() {
        List<JournalRecord> records = journalRepository.findIfLastUserOkLogMoreThen30Days();
        System.out.println("Количество записей: " + records.size());
        for (JournalRecord j : records) {
            System.out.println("Пользователь " + j.getUser().getId());
            System.out.println("Login " + j.getUser().getLogin());
            System.out.println("Дата " + j.getDateTime());
            System.out.println("////////////////////////////////////");
        }
    }
}
