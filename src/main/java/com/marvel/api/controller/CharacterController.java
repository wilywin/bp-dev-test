package com.marvel.api.controller;

import com.marvel.api.dto.Character;
import com.marvel.api.service.CharacterServiceV2;
import jakarta.validation.Valid;
import net.logstash.logback.marker.Markers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.marvel.api.util.LoggerUtil;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/{username}/api/characters")
public class CharacterController {
    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);
    private final CharacterServiceV2 characterServiceV2;
    private final LoggerUtil loggerUtil;

    @Autowired
    public CharacterController(CharacterServiceV2 characterServiceV2, LoggerUtil loggerUtil) {
        this.characterServiceV2 = characterServiceV2;
        this.loggerUtil = loggerUtil;
    }

    @GetMapping
    public List<Character> getAll(@PathVariable String username) {
        logger.debug("Request received: GET /{}/api/characters", username);
        return characterServiceV2.getAll(username);
    }

    @GetMapping("/{id}")
    public Character getById(@PathVariable String username, @PathVariable Integer id) {
        logger.debug("Request received: GET /{}/api/characters/{}", username, id);
        return characterServiceV2.getById(username, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Character create(@PathVariable String username, @Valid @RequestBody Character character) {
        logger.info(
            Markers.append("customLogPayload", Map.of(
                "eventType", "REQUEST",
                "transactionId", "-767668972",
                "serviceName", "marvel-api",
                "serviceVersion", "0.0.1",
                "component", "CharacterController",
                "statusCode", 201,
                "username", username,
                "data", Map.of(
                    "name", character.getName(),
                    "alterego", character.getAlterego(),
                    "description", character.getDescription(),
                    "powers", character.getPowers()
                )
            )),
            "REQUEST : Character[recipient={}, alterego={}, description={}] : -767668972 : INFO : 201 : CharacterController",
            character.getName(), character.getAlterego(), character.getDescription()
        );
        loggerUtil.logWithPayload(logger, username, character, username, "character creation");
        return characterServiceV2.create(username, character);
    }

    @PutMapping("/{id}")
    public Character update(@PathVariable String username, @PathVariable Integer id, @Valid @RequestBody Character character) {
        logger.debug("Request received: PUT /{}/api/characters/{} with body: {}", username, id, character);
        return characterServiceV2.update(username, id, character);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String username, @PathVariable Integer id) {
        logger.debug("Request received: DELETE /{}/api/characters/{}", username, id);
        characterServiceV2.delete(username, id);
    }
}
