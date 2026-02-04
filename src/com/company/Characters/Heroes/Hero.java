package com.company.Characters.Heroes;

import com.company.Characters.GameCharacter;
import com.company.Characters.Monsters.Monster;
import com.company.Items.*;
import com.company.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Hero extends GameCharacter {
    protected int mp;
    protected int maxMp;
    protected int strength;
    protected int dexterity;
    protected int agility;
    protected int experience;
    protected int gold;
    protected List<Item> inventory;
    protected Weapon equippedWeapon;
    protected Armor equippedArmor;
    protected Position position;

    public Hero(String name) {
        super(name);
        this.experience = 0;
        this.gold = 500;
        this.inventory = new ArrayList<>();
        this.inventory.add(new Potion("Health Potion", 50, 1, 50));
        this.inventory.add(new Potion("Mana Potion", 35, 1, 35));
        this.position = new Position(0, 0);
        calculateStats();
    }

    @Override
    protected abstract void calculateStats();

public String getInfo() {
    return String.format("Name: %s (%s), Level: %d, HP: %d/%d, MP: %d/%d, Strength: %d, Dexterity: %d, Agility: %d, Gold: %d",
            name, getClass().getSimpleName(), level, hp, maxHp, mp, maxMp, strength, dexterity, agility, gold);
 }

    public void gainExperience(int amount) {
        experience += amount;
        while (experience >= level * 10) {
            levelUp();
        }
    }

    public void levelUp() {
        level++;
        experience -= level * 10; // Experience points to level up = hero_current_level × 10
        calculateStats();
        System.out.println(name + " leveled up to level " + level + "!");
    }


    public void gainGold(int amount) {
        gold += amount;
        System.out.println(name + " gained " + amount + " gold!");
    }

    public boolean canBuyItem(Item item) {
        return gold >= item.getPrice() && level >= item.getLevel();
    }

    public void buyItem(Item item) {
        if (canBuyItem(item)) {
            gold -= item.getPrice();
            inventory.add(item);
            System.out.println(name + " bought " + item.getName() + "!");
        } else {
            System.out.println(name + " cannot buy " + item.getName() + "!");
        }
    }

    public void sellItem(Item item) {
        if (inventory.remove(item)) {
            gold += item.getPrice() / 2; // Items sell for half of their purchase price
            System.out.println(name + " sold " + item.getName() + " for " + (item.getPrice() / 2) + " gold!");
        } else {
            System.out.println(name + " doesn't have " + item.getName() + " to sell!");
        }
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void equipWeapon(Weapon weapon) {
        if (inventory.contains(weapon)) {
            equippedWeapon = weapon;
            System.out.println(name + " equipped " + weapon.getName() + "!");
        } else {
            System.out.println(name + " doesn't have " + weapon.getName() + " in inventory!");
        }
    }

    public void equipArmor(Armor armor) {
        if (inventory.contains(armor)) {
            equippedArmor = armor;
            System.out.println(name + " equipped " + armor.getName() + "!");
        } else {
            System.out.println(name + " doesn't have " + armor.getName() + " in inventory!");
        }
    }

    public void usePotion(Potion potion) {
        if (inventory.contains(potion)) {
            potion.use(this);
            inventory.remove(potion);
        } else {
            System.out.println(name + " doesn't have " + potion.getName() + " in inventory!");
        }
    }

    public int getAttackDamage() {
        int weaponDamage = equippedWeapon != null ? equippedWeapon.getDamage(level) : 0;

        // Base damage calculation with increased multiplier
        int baseDamage = (int)((strength + weaponDamage) * 0.3); // Increased multiplier for more impactful base damage

        // Add randomness to the damage (between 90% and 110% of base damage)
        double randomFactor = 0.9 + (Math.random() * 0.2); // Random factor between 0.9 and 1.1
        int finalDamage = (int)(baseDamage * randomFactor);

        // Critical hit chance (10%)
        if (Math.random() < 0.10) {
            System.out.println("Critical hit!");
            finalDamage *= 2;
        }

        // Ensure minimum damage is at least a reasonable value
        return Math.max(15, finalDamage);
    }
    public int castSpell(Spell spell, Monster target) {
        if (mp >= spell.getManaCost()) {
            mp -= spell.getManaCost();
            int damage = (int)(spell.getDamage() + (dexterity / 10000.0) * spell.getDamage());
            target.takeDamage(damage);
            spell.applyEffect(target);
            return damage;
        } else {
            System.out.println(name + " doesn't have enough mana to cast " + spell.getName() + "!");
            return 0;
        }
    }

    public List<Spell> getSpells() {
        return inventory.stream()
                .filter(item -> item instanceof Spell)
                .map(item -> (Spell) item)
                .collect(Collectors.toList());
    }

    public List<Potion> getPotions() {
        return inventory.stream()
                .filter(item -> item instanceof Potion)
                .map(item -> (Potion) item)
                .collect(Collectors.toList());
    }

    public List<Weapon> getWeapons() {
        return inventory.stream()
                .filter(item -> item instanceof Weapon)
                .map(item -> (Weapon) item)
                .collect(Collectors.toList());
    }
    public boolean dodge() {
        return Math.random() < getDodgeChance();
    }


    // Add reduceGold method
    public void reduceGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            System.out.println(name + " spent " + amount + " gold.");
        } else {
            System.out.println(name + " does not have enough gold!");
        }
    }



    public void restoreMana(int amount) {
        setMp(Math.min(mp + amount, maxMp));
    }

    public void revive() {
        hp = maxHp / 2;  // Revive with half HP
        mp = maxMp / 2;  // Revive with half MP
        System.out.println(name + " has been revived!");
    }

    public List<Armor> getArmors() {
        return inventory.stream()
                .filter(item -> item instanceof Armor)
                .map(item -> (Armor) item)
                .collect(Collectors.toList());
    }

    public double getDodgeChance() {
        return agility * 0.002;
    }

    public void move(Position newPosition) {
        this.position = newPosition;
    }

    // Getters and setters
    public int getMp() { return mp; }
    public void setMp(int mp) { this.mp = Math.min(mp, maxMp); }
    public int getMaxMp() { return maxMp; }
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getAgility() { return agility; }
    public int getExperience() { return experience; }
    public int getGold() { return gold; }
    public List<Item> getInventory() { return inventory; }
    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public Armor getEquippedArmor() { return equippedArmor; }
    public Position getPosition() { return position; }

    public void takeDamage(int damage) {
        if (!dodge()) {
            hp -= Math.max(1, damage - (equippedArmor != null ? equippedArmor.getDamageReduction() : 0));
            hp = Math.max(0, hp); // Ensure HP doesn't go below 0
        } else {
            System.out.println(name + " dodged the attack!");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (Level %d %s) - HP: %d/%d, MP: %d/%d, Strength: %d, Dexterity: %d, Agility: %d, Experience: %d, Gold: %d",
                name, level, getClass().getSimpleName(), hp, maxHp, mp, maxMp, strength, dexterity, agility, experience, gold);
    }
}