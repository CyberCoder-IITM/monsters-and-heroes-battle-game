package com.company.Characters.Heroes;

import com.company.Characters.Monsters.Monster;
import com.company.Items.Spell;

public class Paladin extends Hero {
    public Paladin(String name) {
        super(name);
    }

    @Override
    protected void calculateStats() {
        maxHp = level * 100;
        hp = maxHp;
        maxMp = level * 80;
        mp = maxMp;
        strength = (int) (level * 9 * 1.05);   // Favored in strength
        agility = level * 6;
        dexterity = (int) (level * 8 * 1.05);  // Favored in dexterity
    }

    @Override
    public void levelUp() {
        super.levelUp();
        // Paladins get an extra 5% increase in strength and dexterity when leveling up
        strength = (int) (strength * 1.05);
        dexterity = (int) (dexterity * 1.05);
    }

    @Override
    public int getAttackDamage() {
        // Paladins get a 5% bonus to their attack damage
        return (int) (super.getAttackDamage() * 1.05);
    }

    @Override
    public int castSpell(Spell spell, Monster target) {
        // Paladins get a 5% bonus to their spell damage
        int damage = super.castSpell(spell, target);
        return (int) (damage * 1.05);
    }

    @Override
    public String toString() {
        return "Paladin " + super.toString();
    }
}