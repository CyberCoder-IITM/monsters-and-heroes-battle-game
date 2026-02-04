package com.company.Characters.Monsters;

public class Spirit extends Monster {
    public Spirit(String name, int level) {
        super(name, level);
    }

    @Override
    protected void calculateStats() {
        maxHp = level * 100;
        hp = maxHp;
        baseDamage = level * 18;
        defense = level * 8;
        dodgeChance = 0.3 * 0.01;  // Increased dodge chance, adjusted as per PDF formula
    }

    @Override
    public boolean dodge() {
        // Spirits have an additional chance to dodge
        return super.dodge() || Math.random() < 0.1;  // 10% extra dodge chance
    }

    @Override
    public String toString() {
        return "Spirit " + super.toString();
    }
}