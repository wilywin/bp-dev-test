package com.marvel.api.repository.impl;

import com.marvel.api.dto.Character;
import com.marvel.api.repository.CharacterRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryCharacterRepository implements CharacterRepository {
    private final Map<Integer, Character> store = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public List<Character> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Character> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Character save(Character character) {
        if (character.getId() == null) {
            character.setId(idCounter.getAndIncrement());
        }
        store.put(character.getId(), character);
        return character;
    }

    @Override
    public boolean existsById(Integer id) {
        return store.containsKey(id);
    }

    @Override
    public boolean existsByName(String name) {
        return store.values().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name));
    }

    @Override
    public void deleteById(Integer id) {
        store.remove(id);
    }
}
