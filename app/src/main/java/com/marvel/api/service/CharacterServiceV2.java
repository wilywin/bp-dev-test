package com.marvel.api.service;

import com.marvel.api.dto.Character;
import java.util.List;

public interface CharacterServiceV2 {
    List<Character> getAll(String username);
    Character getById(String username, Integer id);
    Character create(String username, Character character);
    Character update(String username, Integer id, Character character);
    void delete(String username, Integer id);
}
