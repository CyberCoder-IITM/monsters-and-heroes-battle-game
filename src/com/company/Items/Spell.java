package com.company.Items;

import com.company.Characters.Heroes.Hero;
import com.company.Characters.Monsters.Monster;

public class Spell extends Item {
    private int damage;
    private int manaCost;
    private String spellType;

    public Spell(String name, int price, int level, int damage, int manaCost, String spellType) {
        super(name, price, level,damage);
        this.damage = damage;
        this.manaCost = manaCost;
        this.spellType = spellType;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public String getSpellType() {
        return spellType;
    }

    public void applyEffect(Monster target) {
        switch (spellType.toLowerCase()) {
            case "ice":
                target.reduceDamage();
                System.out.println(target.getName() + "'s damage has been reduced!");
                break;
            case "fire":
                target.reduceDefense();
                System.out.println(target.getName() + "'s defense has been reduced!");
                break;
            case "lightning":
                target.reduceDodgeChance();
                System.out.println(target.getName() + "'s dodge chance has been reduced!");
                break;
            default:
                System.out.println("Unknown spell type: " + spellType);
        }
    }

    @Override
    public String getItemType() {
        return "Spell";
    }

    @Override
    public void use(Hero hero) {
        super.use();
        System.out.println(getName() + " spell has been cast by " + hero.getName() + "!");
    }

    @Override
    public String toString() {
        return String.format("%s (Level %d %s Spell) - Damage: %d, Mana Cost: %d, Price: %d gold, Uses left: %d",
                getName(), getLevel(), spellType, damage, manaCost, getPrice(), getRemainingUses());
    }
}
