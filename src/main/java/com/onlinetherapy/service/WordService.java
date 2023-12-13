package com.onlinetherapy.service;

import com.onlinetherapy.model.dtos.CreateWordDTO;
import com.onlinetherapy.model.entity.Language;
import com.onlinetherapy.model.entity.User;
import com.onlinetherapy.model.entity.Word;
import com.onlinetherapy.model.enums.LanguageName;
import com.onlinetherapy.repo.LanguageRepository;
import com.onlinetherapy.repo.UserRepository;
import com.onlinetherapy.repo.WordRepository;
import com.onlinetherapy.sessions.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordService {

    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;
    private final LoggedUser loggedUser;

    public WordService(UserRepository userRepository, LanguageRepository languageRepository,
                       WordRepository wordRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
        this.loggedUser = loggedUser;
    }

    public boolean create(@Valid CreateWordDTO createWordDTO) {
        Optional<User> owner = this.userRepository.findById(this.loggedUser.getId());

        if (!owner.isPresent()) {
            return false;
        }
        LanguageName languageName = LanguageName.valueOf(createWordDTO.getLanguage());
        Language language = this.languageRepository.findByName(languageName);


        Word word = new Word();
        word.setAddedBy(owner.get());
        word.setTerm(createWordDTO.getTerm());
        word.setTranslation(createWordDTO.getTranslation());
        word.setExample(createWordDTO.getExample());
        word.setInputDate(createWordDTO.getInputDate());
        word.setLanguage(language);

        this.wordRepository.save(word);
        this.userRepository.save(owner.get());
        this.languageRepository.save(word.getLanguage());
        return true;
    }


//    public void deleteAllWords(@Valid CreateWordDTO createWordDTO) {
//        LanguageName languageName = LanguageName.valueOf(createWordDTO.getLanguage());
//        Language language = this.languageRepository.findByName(languageName);


}
