package com.marvel.api.service;

import com.marvel.api.dto.Character;
import java.util.List;

public interface CharacterService {
    List<Character> getAll();
    Character getById(Integer id);
    Character create(Character character);
    Character update(Integer id, Character character);
    void delete(Integer id);
}
