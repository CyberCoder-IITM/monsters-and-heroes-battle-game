package com.company.Items;


import com.company.Characters.Heroes.Hero;

public class Weapon extends Item {
    private int damage;
    private int handsRequired;

    public Weapon(String name, int price, int level, int damage, int handsRequired) {
        super(name, price, level, 1); // Weapons typically don't have uses, so set it to 1
        this.damage = damage;
        this.handsRequired = handsRequired;
    }

    public int getDamage(int heroLevel) {
        return (int)(damage * (1 + (heroLevel * 0.05)));
    }

    public int getHandsRequired() {
        return handsRequired;
    }

    @Override
    public void use(Hero hero) {
        hero.equipWeapon(this);
        System.out.println(hero.getName() + " equipped " + getName() + "!");
    }

    @Override
    public String getItemType() {
        return "Weapon";
    }

    public int calculateDamage(int strength, int heroLevel) {
        int scaledDamage = getDamage(heroLevel);
        if (handsRequired == 1) {
            return (int)((strength + scaledDamage) * 0.05);
        } else {
            // Two-handed weapons get a 50% damage bonus
            return (int)((strength + scaledDamage) * 0.05 * 1.5);
        }
    }

    public void upgrade() {
        // Increase the weapon's damage by 10% for each upgrade
        this.damage = (int)(this.damage * 1.10);
        System.out.println(getName() + " has been upgraded! New damage: " + this.damage);
    }

    @Override
    public String toString() {
        return String.format("%s (Level %d Weapon) - Damage: %d, Hands Required: %d, Price: %d gold",
                getName(), getLevel(), damage, handsRequired, getPrice());
    }
}