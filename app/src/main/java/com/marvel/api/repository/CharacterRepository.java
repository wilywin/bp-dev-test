package com.marvel.api.repository;

import com.marvel.api.dto.Character;
import java.util.List;
import java.util.Optional;

public interface CharacterRepository {
    List<Character> findAll();
    Optional<Character> findById(Integer id);
    Character save(Character character);
    boolean existsById(Integer id);
    void deleteById(Integer id);
    boolean existsByName(String name);
}
