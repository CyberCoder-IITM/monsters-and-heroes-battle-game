package com.company.Characters.Heroes;

import com.company.Characters.Monsters.Monster;
import com.company.Items.Spell;

public class Sorcerer extends Hero {
    public Sorcerer(String name) {
        super(name);
        inventory.add(new Spell("Fireball", 100, 1, 30, 25, "fire"));
        inventory.add(new Spell("Ice Shard", 100, 1, 25, 20, "ice"));
    }

    @Override
    protected void calculateStats() {
        maxHp = level * 100;
        hp = maxHp;
        maxMp = level * 120;  // Higher mana for spellcasting
        mp = maxMp;
        strength = level * 5;
        agility = (int) (level * 8 * 1.05);    // Favored in agility
        dexterity = (int) (level * 10 * 1.05); // Favored in dexterity
    }

    @Override
    public void levelUp() {
        super.levelUp();
        // Sorcerers get an extra 5% increase in dexterity and agility when leveling up
        dexterity = (int) (dexterity * 1.05);
        agility = (int) (agility * 1.05);
    }

    @Override
    public int castSpell(Spell spell, Monster target) {
        // Sorcerers get a 10% bonus to their spell damage
        int damage = super.castSpell(spell, target);
        return (int) (damage * 1.1);
    }

    @Override
    public String toString() {
        return "Sorcerer " + super.toString();
    }
}