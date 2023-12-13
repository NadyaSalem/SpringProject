package com.onlinetherapy.init;

import com.onlinetherapy.model.entity.Language;
import com.onlinetherapy.model.enums.LanguageName;
import com.onlinetherapy.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LanguageSeeder implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    public LanguageSeeder(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (this.languageRepository.count() == 0) {

            List<Language> languages = new ArrayList<>();

            Arrays.stream(LanguageName.values())
                    .forEach(LanguageName -> {
                        Language language = new Language();
                        language.setName(LanguageName);
                        languages.add(language);

                    });
            this.languageRepository.saveAll(languages);
        }
    }
}
