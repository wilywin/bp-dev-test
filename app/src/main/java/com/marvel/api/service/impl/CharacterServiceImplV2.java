package com.marvel.api.service.impl;

import com.marvel.api.dto.Character;
import com.marvel.api.exception.ResourceNotFoundException;
import com.marvel.api.repository.CharacterRepositoryV2;
import com.marvel.api.service.CharacterServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.marvel.api.util.MarvelConstants.CHARACTER_NOT_FOUND;
import static com.marvel.api.util.MarvelConstants.DUPLICATE_CHARACTER_NAME;

@Service
public class CharacterServiceImplV2 implements CharacterServiceV2 {
    private static final Logger logger = LoggerFactory.getLogger(CharacterServiceImplV2.class);
    private final CharacterRepositoryV2 repository;

    @Autowired
    public CharacterServiceImplV2(CharacterRepositoryV2 repository) {
        this.repository = repository;
    }

    @Override
    public List<Character> getAll(String username) {
        logger.info("Fetching all characters for user: {}", username);
        return repository.findAll(username);
    }

    @Override
    public Character getById(String username, Integer id) {
        logger.info("Fetching character with id: {} for user: {}", id, username);
        return repository.findById(username, id)
                .orElseThrow(() -> new ResourceNotFoundException(CHARACTER_NOT_FOUND));
    }

    @Override
    public Character create(String username, Character character) {
        logger.info("Creating character with name: {} for user: {}", character.getName(), username);
        if (repository.existsByName(username, character.getName())) {
            logger.warn("Attempt to create duplicate character name: {} for user: {}", character.getName(), username);
            throw new com.marvel.api.exception.DuplicateCharacterNameException(DUPLICATE_CHARACTER_NAME);
        }
        character.setId(null);
        Character saved = repository.save(username, character);
        logger.info("Character created with id: {} for user: {}", saved.getId(), username);
        return saved;
    }

    @Override
    public Character update(String username, Integer id, Character character) {
        logger.info("Updating character with id: {} for user: {}", id, username);
        if (!repository.existsById(username, id)) {
            logger.warn("Attempt to update non-existent character id: {} for user: {}", id, username);
            throw new ResourceNotFoundException(CHARACTER_NOT_FOUND);
        }
        character.setId(id);
        Character updated = repository.save(username, character);
        logger.info("Character updated with id: {} for user: {}", updated.getId(), username);
        return updated;
    }

    @Override
    public void delete(String username, Integer id) {
        logger.info("Deleting character with id: {} for user: {}", id, username);
        if (!repository.existsById(username, id)) {
            logger.warn("Attempt to delete non-existent character id: {} for user: {}", id, username);
            throw new ResourceNotFoundException(CHARACTER_NOT_FOUND);
        }
        repository.deleteById(username, id);
        logger.info("Character deleted with id: {} for user: {}", id, username);
    }
}
