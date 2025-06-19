package com.marvel.api.repository;

import com.marvel.api.dto.Character;
import java.util.List;
import java.util.Optional;

public interface CharacterRepositoryV2 {
    List<Character> findAll(String username);
    Optional<Character> findById(String username, Integer id);
    Character save(String username, Character character);
    boolean existsById(String username, Integer id);
    void deleteById(String username, Integer id);
    boolean existsByName(String username, String name);
}
