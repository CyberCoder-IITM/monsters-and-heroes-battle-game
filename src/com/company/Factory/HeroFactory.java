package com.company.Factory;
import com.company.Characters.GameCharacter;
import com.company.Characters.Heroes.Hero;
import com.company.Characters.Heroes.Paladin;
import com.company.Characters.Heroes.Sorcerer;
import com.company.Characters.Heroes.Warrior;

import java.util.Random;

public class HeroFactory implements CharacterFactory {
    @Override
    public GameCharacter createCharacter(String name, int level) {
        Random rand = new Random();
        int type = rand.nextInt(3);
        Hero hero;

        switch (type) {
            case 0:
                hero = new Warrior(name);
                break;
            case 1:
                hero = new Sorcerer(name);
                break;
            case 2:
                hero = new Paladin(name);
                break;
            default:
                hero = new Warrior(name);
        }

        // Set the hero's level and recalculate stats
        for (int i = 1; i < level; i++) {
            hero.levelUp();
        }

        return hero;
    }

    public Hero createSpecificHero(String name, String type) {
        switch (type.toLowerCase()) {
            case "warrior":
                return new Warrior(name);
            case "sorcerer":
                return new Sorcerer(name);
            case "paladin":
                return new Paladin(name);
            default:
                throw new IllegalArgumentException("Invalid hero type: " + type);
        }
    }
}