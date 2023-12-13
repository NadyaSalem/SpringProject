package com.onlinetherapy.repo;

import com.onlinetherapy.model.entity.Language;
import com.onlinetherapy.model.enums.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findByName(LanguageName languageName);
}
