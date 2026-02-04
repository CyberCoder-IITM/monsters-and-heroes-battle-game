package com.company.Characters.Monsters;


public class Dragon extends Monster {
    public Dragon(String name, int level) {
        super(name, level);
    }

    @Override
    protected void calculateStats() {
        maxHp = level * 100;
        hp = maxHp;
        baseDamage = (int)(level * 20 * 1.2);  // Dragons have increased base damage (20% increase)
        defense = level * 10;
        dodgeChance = 0.1;
    }

    @Override
    public int attack() {
        // Dragons have a chance to perform a special fire breath attack
        if (Math.random() < 0.2) {  // 20% chance for special attack
            System.out.println(name + " uses fire breath!");
            return (int)(baseDamage * 1.5);  // Fire breath does 50% more damage
        }
        return super.attack();
    }

    @Override
    public String toString() {
        return "Dragon " + super.toString();
    }
}
