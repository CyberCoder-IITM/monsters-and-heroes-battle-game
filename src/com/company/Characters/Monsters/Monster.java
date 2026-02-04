package com.company.Characters.Monsters;

import com.company.Characters.GameCharacter;

public abstract class Monster extends GameCharacter {
    protected int baseDamage;
    protected int defense;
    protected double dodgeChance;

    public Monster(String name, int level) {
        super(name);
        this.level = level;
        calculateStats();
    }

    @Override
    protected abstract void calculateStats();

    public int attack() {
        int minDamage = (int) (baseDamage * 0.8);
        int maxDamage = (int) (baseDamage * 1.2);
        return (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
    }

//    public int attack() {
//        return baseDamage;
//    }


    public int defend(int incomingDamage) {
        int damageTaken = Math.max(0, incomingDamage - defense);
        hp -= damageTaken;
        return damageTaken;
    }

    public boolean dodge() {
        return Math.random() < dodgeChance;
    }

    public void takeDamage(int damage) {
        if (!dodge()) {
//            hp -= Math.max(0, damage - defense);
            hp -= Math.max(1, damage - defense);
        } else {
            System.out.println(name + " dodged the attack!");
        }
    }

    public void reduceDamage() {
        baseDamage = (int) (baseDamage * 0.9); // Reduce damage by 10%
    }

    public void reduceDefense() {
        defense = (int) (defense * 0.9); // Reduce defense by 10%
    }

    public void reduceDodgeChance() {
        dodgeChance *= 0.9; // Reduce dodge chance by 10%
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getDefense() {
        return defense;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    @Override
    public String toString() {
        return String.format("%s (Level %d %s) - HP: %d/%d, Damage: %d, Defense: %d, Dodge Chance: %.2f%%",
                name, level, getClass().getSimpleName(), hp, maxHp, baseDamage, defense, dodgeChance * 100);
    }
}
