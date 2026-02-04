package com.company.Items;

import com.company.Characters.Heroes.Hero;

public class Potion extends Item {
    private int healingAmount;

    public Potion(String name, int price, int level, int healingAmount) {
        super(name, price, level, 1); // Potions typically have only one use
        this.healingAmount = healingAmount;
    }

    @Override
    public void use(Hero hero) {
        if (isUsable()) {
            hero.setHp(Math.min(hero.getHp() + healingAmount, hero.getMaxHp()));
            System.out.println(hero.getName() + " used " + getName() + " and healed for " + healingAmount + " HP.");
            super.use(); // Decrease remaining uses
        } else {
            System.out.println("This potion has already been used.");
        }
    }

    @Override
    public String getItemType() {
        return "Potion";
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public String toString() {
        return String.format("%s (Level %d Potion) - Heals: %d HP, Price: %d gold, Uses left: %d",
                getName(), getLevel(), healingAmount, getPrice(), getRemainingUses());
    }
}