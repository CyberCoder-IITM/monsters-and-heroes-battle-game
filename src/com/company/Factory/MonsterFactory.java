package com.company.Factory;

import com.company.Characters.GameCharacter;
import com.company.Characters.Monsters.Dragon;
import com.company.Characters.Monsters.Exoskeleton;
import com.company.Characters.Monsters.Monster;
import com.company.Characters.Monsters.Spirit;

import java.util.Random;

public class MonsterFactory implements CharacterFactory {
    @Override
    public GameCharacter createCharacter(String name, int level) {
        Random rand = new Random();
        int type = rand.nextInt(3);
        Monster monster;

        switch (type) {
            case 0:
                monster = new Dragon(name, level);
                break;
            case 1:
                monster = new Exoskeleton(name, level);
                break;
            case 2:
                monster = new Spirit(name, level);
                break;
            default:
                monster = new Dragon(name, level);
        }

        return monster;
    }

    public Monster createSpecificMonster(String name, String type, int level) {
        switch (type.toLowerCase()) {
            case "dragon":
                return new Dragon(name, level);
            case "exoskeleton":
                return new Exoskeleton(name, level);
            case "spirit":
                return new Spirit(name, level);
            default:
                throw new IllegalArgumentException("Invalid monster type: " + type);
        }
    }
}