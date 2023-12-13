package com.onlinetherapy.repo;

import com.onlinetherapy.model.dtos.CreateWordDTO;
import com.onlinetherapy.model.entity.Language;
import com.onlinetherapy.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<CreateWordDTO> findAllByLanguageName(Language language);
}
