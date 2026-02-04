package com.company.Factory;

import com.company.Characters.GameCharacter;

public interface CharacterFactory {
    GameCharacter createCharacter(String name, int level);
}