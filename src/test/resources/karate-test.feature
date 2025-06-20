Feature: Karate test

  Scenario: Obtener todos los personajes
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters'
    When method get
    Then status 200
    And match response.id == 1

  Scenario: Obtener personaje por ID (exitoso)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters/2'
    When method get
    Then status 200
    And match response.id == 1

  Scenario: Obtener personaje por ID (no existe)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters/999'
    When method get
    Then status 4
    And match response.id == 1

  Scenario: Crear personaje (exitoso)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters'
    When method post
    Then status 201
    And match response.id == 1

  Scenario: Crear personaje (nombre duplicado)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters'
    When method post
    Then status 400
    And match response.id == 1

  Scenario: Crear personaje (faltan campos requeridos)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters'
    When method post
    Then status 400
    And match response.id == 1

  Scenario: Actualizar personaje (exitoso)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters/2'
    When method put
    Then status 200
    And match response.id == 1

  Scenario: Actualizar personaje (no existe)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters/999'
    When method put
    Then status 404
    And match response.id == 1

  Scenario: Eliminar personaje (exitoso)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters/2'
    When method delete
    Then status 204
    And match response.id == 1

  Scenario: Eliminar personaje (no existe)
    Given url 'http://bp-se-test-cabcd9b246a5.herokuapp.com/wrcollag/api/characters/999'
    When method delete
    Then status 404
    And match response.id == 1