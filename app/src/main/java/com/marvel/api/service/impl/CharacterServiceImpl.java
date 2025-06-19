package com.marvel.api.service.impl;

import static com.marvel.api.util.MarvelConstants.CHARACTER_NOT_FOUND;
import static com.marvel.api.util.MarvelConstants.DUPLICATE_CHARACTER_NAME;

import com.marvel.api.dto.Character;
import com.marvel.api.exception.ResourceNotFoundException;
import com.marvel.api.repository.CharacterRepository;
import com.marvel.api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);
    private final CharacterRepository repository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Character> getAll() {
        logger.info("Fetching all characters");
        return repository.findAll();
    }

    @Override
    public Character getById(Integer id) {
        logger.info("Fetching character with id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CHARACTER_NOT_FOUND));
    }

    @Override
    public Character create(Character character) {
        logger.info("Creating character with name: {}", character.getName());
        if (repository.existsByName(character.getName())) {
            logger.warn("Attempt to create duplicate character name: {}", character.getName());
            throw new com.marvel.api.exception.DuplicateCharacterNameException(DUPLICATE_CHARACTER_NAME);
        }
        character.setId(null);
        Character saved = repository.save(character);
        logger.info("Character created with id: {}", saved.getId());
        return saved;
    }

    @Override
    public Character update(Integer id, Character character) {
        logger.info("Updating character with id: {}", id);
        if (!repository.existsById(id)) {
            logger.warn("Attempt to update non-existent character id: {}", id);
            throw new ResourceNotFoundException(CHARACTER_NOT_FOUND);
        }
        character.setId(id);
        Character updated = repository.save(character);
        logger.info("Character updated with id: {}", updated.getId());
        return updated;
    }

    @Override
    public void delete(Integer id) {
        logger.info("Deleting character with id: {}", id);
        if (!repository.existsById(id)) {
            logger.warn("Attempt to delete non-existent character id: {}", id);
            throw new ResourceNotFoundException(CHARACTER_NOT_FOUND);
        }
        repository.deleteById(id);
        logger.info("Character deleted with id: {}", id);
    }
}
