package com.marvel.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoggerUtil {
    private static final String MARKER_NAME = "customLogPayload";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void logWithPayload(Logger logger, String message, Object payload, String logLevel, Object... args) {
        Marker marker = MarkerFactory.getMarker(MARKER_NAME);
        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            jsonPayload = "{\"error\":\"No se pudo serializar el payload a JSON\"}";
        }
        switch (logLevel.toLowerCase()) {
            case "debug" -> {
                logger.debug(marker, message, args);
                logger.debug(marker, "Payload: {}", jsonPayload);
            }
            case "warn" -> {
                logger.warn(marker, message, args);
                logger.warn(marker, "Payload: {}", jsonPayload);
            }
            case "error" -> {
                logger.error(marker, message, args);
                logger.error(marker, "Payload: {}", jsonPayload);
            }
            default -> {
                logger.info(
                        Markers.append("customLogPayload",
                                objectToMap(payload)), message);
            }
        }
    }

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "{\"error\":\"No se pudo serializar el objeto a JSON\"}";
        }
    }

    public Map<String, Object> objectToMap(Object object) {
        try {
            return objectMapper.convertValue(object, Map.class);
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", "No se pudo convertir el objeto a Map");
            return errorMap;
        }
    }
}
