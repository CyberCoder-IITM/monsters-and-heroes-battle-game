package com.company.Characters.Heroes;

public class Warrior extends Hero {
    public Warrior(String name) {
        super(name);
    }

    @Override
    protected void calculateStats() {
        maxHp = level * 100;
        hp = maxHp;
        maxMp = level * 50;
        mp = maxMp;
        strength = (int)(level * 10 * 1.05);  // Favored in strength
        agility = (int)(level * 8 * 1.05);    // Favored in agility
        dexterity = level * 5;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        // Warriors get an extra 5% increase in strength and agility when leveling up
        strength = (int)(strength * 1.05);
        agility = (int)(agility * 1.05);
    }

    @Override
    public int getAttackDamage() {
        // Warriors get a 10% bonus to their attack damage
        return (int)(super.getAttackDamage() * 1.1);
    }

    @Override
    public String toString() {
        return "Warrior " + super.toString();
    }
}