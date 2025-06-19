package com.marvel.api.repository.impl;

import com.marvel.api.dto.Character;
import com.marvel.api.repository.CharacterRepositoryV2;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryCharacterRepositoryV2 implements CharacterRepositoryV2 {
    // Map<username, Map<characterId, Character>>
    private final Map<String, Map<Integer, Character>> userStore = new ConcurrentHashMap<>();
    private final Map<String, AtomicInteger> userIdCounters = new ConcurrentHashMap<>();

    @Override
    public List<Character> findAll(String username) {
        return new ArrayList<>(userStore.getOrDefault(username, Collections.emptyMap()).values());
    }

    @Override
    public Optional<Character> findById(String username, Integer id) {
        return Optional.ofNullable(userStore.getOrDefault(username, Collections.emptyMap()).get(id));
    }

    @Override
    public Character save(String username, Character character) {
        userStore.putIfAbsent(username, new ConcurrentHashMap<>());
        userIdCounters.putIfAbsent(username, new AtomicInteger(1));
        if (character.getId() == null) {
            character.setId(userIdCounters.get(username).getAndIncrement());
        }
        userStore.get(username).put(character.getId(), character);
        return character;
    }

    @Override
    public boolean existsById(String username, Integer id) {
        return userStore.getOrDefault(username, Collections.emptyMap()).containsKey(id);
    }

    @Override
    public void deleteById(String username, Integer id) {
        userStore.getOrDefault(username, Collections.emptyMap()).remove(id);
    }

    @Override
    public boolean existsByName(String username, String name) {
        return userStore.getOrDefault(username, Collections.emptyMap()).values().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name));
    }
}
