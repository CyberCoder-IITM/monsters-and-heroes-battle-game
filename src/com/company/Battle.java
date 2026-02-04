package com.company;
import com.company.Items.*;
import com.company.Characters.Heroes.Hero;
import com.company.Characters.Heroes.Warrior;
import com.company.Characters.Monsters.Monster;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Battle {
    private List<Hero> heroes;
    private List<Monster> monsters;
    private boolean heroesWon;
    private Scanner scanner;

    public Battle(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.heroesWon = false;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Battle starts!");
        while (!isBattleOver()) {
            heroTurn();
            if (!isBattleOver()) {
                monsterTurn();
            }
            endRound();
        }
        endBattle();
    }

    private boolean isBattleOver() {
        return areAllHeroesFainted() || areAllMonstersDead();
    }

    private void heroTurn() {
        for (Hero hero : heroes) {
            if (hero.isAlive()) {
                performHeroAction(hero);
            }
        }
    }

    private void performHeroAction(Hero hero) {
        System.out.println("\n" + hero.getName() + "'s turn:");
        System.out.println("1. Attack");

        if (!(hero instanceof Warrior)) {
            System.out.println("2. Cast Spell");
            System.out.println("3. Use Potion");
            System.out.println("4. Change Weapon/Armor");
            System.out.println("5. Show Info");
        } else {
            System.out.println("2. Use Potion");
            System.out.println("3. Change Weapon/Armor");
            System.out.println("4. Show Info");
        }

        int choice = -1;

        // Loop until valid input is provided
        while (choice == -1) {
            try {
                System.out.print("Choose an action: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // Handle valid choices
                switch (choice) {
                    case 1:
                        heroAttack(hero);
                        break;
                    case 2:
                        if (hero instanceof Warrior) {
                            heroUsePotion(hero);
                        } else {
                            heroCastSpell(hero);
                        }
                        break;
                    case 3:
                        if (hero instanceof Warrior) {
                            heroChangeEquipment(hero);
                        } else {
                            heroUsePotion(hero);
                        }
                        break;
                    case 4:
                        if (hero instanceof Warrior) {
                            showBattleInfo();
                            performHeroAction(hero); // Allow hero to choose again after showing info
                        } else {
                            heroChangeEquipment(hero);
                        }
                        break;
                    case 5:
                        if (!(hero instanceof Warrior)) {
                            showBattleInfo();
                            performHeroAction(hero); // Allow hero to choose again after showing info
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                            choice = -1; // Reset choice to allow retry
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        choice = -1; // Reset choice to allow retry
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                choice = -1; // Reset choice to allow retry
            }
        }
    }

    private void heroAttack(Hero hero) {
        Monster target = selectMonsterTarget();
        int damage = hero.getAttackDamage();
        if (!target.dodge()) {
            target.takeDamage(damage);
            System.out.println(hero.getName() + " attacked " + target.getName() + " for " + damage + " damage!");
        } else {
            System.out.println(target.getName() + " dodged the attack!");
        }
    }

    private void heroCastSpell(Hero hero) {
        List<Spell> spells = hero.getSpells();
        if (spells.isEmpty()) {
            System.out.println(hero.getName() + " has no spells to cast!");
            return;
        }

        System.out.println("Choose a spell to cast:");
        for (int i = 0; i < spells.size(); i++) {
            System.out.println((i + 1) + ". " + spells.get(i));
        }

        int choice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (choice >= 0 && choice < spells.size()) {
            Spell spell = spells.get(choice);
            Monster target = selectMonsterTarget();
            int damage = hero.castSpell(spell, target);
            if (damage > 0) {
                System.out.println(hero.getName() + " cast " + spell.getName() + " on " + target.getName() + " for " + damage + " damage!");
//                spell.applyEffect(target);
            } else {
                System.out.println(hero.getName() + " doesn't have enough mana to cast " + spell.getName() + "!");
            }
        } else {
            System.out.println("Invalid spell choice.");
        }
    }

    private void heroUsePotion(Hero hero) {
        List<Potion> potions = hero.getPotions();
        if (potions.isEmpty()) {
            System.out.println(hero.getName() + " has no potions to use!");
            performHeroAction(hero); // Allow the hero to choose another action
            return;
        }

        System.out.println("Choose a potion to use:");
        for (int i = 0; i < potions.size(); i++) {
            System.out.println((i + 1) + ". " + potions.get(i));
        }

        int choice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (choice >= 0 && choice < potions.size()) {
            Potion potion = potions.get(choice);
            hero.usePotion(potion);
            System.out.println(hero.getName() + " used " + potion.getName() + "!");
        } else {
            System.out.println("Invalid potion choice.");
            performHeroAction(hero); // Allow the hero to choose another action
        }
    }

    private void heroChangeEquipment(Hero hero) {
        System.out.println("1. Change Weapon");
        System.out.println("2. Change Armor");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                changeWeapon(hero);
                break;
            case 2:
                changeArmor(hero);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void changeWeapon(Hero hero) {
        List<Weapon> weapons = hero.getWeapons();
        if (weapons.isEmpty()) {
            System.out.println(hero.getName() + " has no weapons to equip!");
            performHeroAction(hero);
            return;
        }

        System.out.println("Choose a weapon to equip:");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ". " + weapons.get(i));
        }

        int choice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (choice >= 0 && choice < weapons.size()) {
            Weapon weapon = weapons.get(choice);
            hero.equipWeapon(weapon);
            System.out.println(hero.getName() + " equipped " + weapon.getName() + "!");
        } else {
            System.out.println("Invalid weapon choice.");
        }
    }

    private void changeArmor(Hero hero) {
        List<Armor> armors = hero.getArmors();
        if (armors.isEmpty()) {
            System.out.println(hero.getName() + " has no armor to equip!");
            performHeroAction(hero);
            return;
        }

        System.out.println("Choose an armor to equip:");
        for (int i = 0; i < armors.size(); i++) {
            System.out.println((i + 1) + ". " + armors.get(i));
        }

        int choice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (choice >= 0 && choice < armors.size()) {
            Armor armor = armors.get(choice);
            hero.equipArmor(armor);
            System.out.println(hero.getName() + " equipped " + armor.getName() + "!");
        } else {
            System.out.println("Invalid armor choice.");
        }
    }


private Monster selectMonsterTarget() {
    int choice = -1;

    while (choice == -1) {
        System.out.println("Select a monster to target:");
        for (int i = 0; i < monsters.size(); i++) {
            System.out.println((i + 1) + ". " + monsters.get(i).getName());
        }

        try {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt() - 1; // Adjust for zero-indexing
            scanner.nextLine(); // Consume newline

            if (choice >= 0 && choice < monsters.size()) {
                return monsters.get(choice);
            } else {
                System.out.println("Invalid monster choice. Please try again.");
                choice = -1; // Reset choice to allow retry
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            choice = -1; // Reset choice to allow retry
        }
    }

    // This line should never be reached due to the loop logic
    return null;
}

    private void monsterTurn() {
        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                Hero target = heroes.get((int) (Math.random() * heroes.size()));
                int damage = monster.attack();
                if (!target.dodge()) {
                    target.takeDamage(damage);
                    System.out.println(monster.getName() + " attacked " + target.getName() + " for " + damage + " damage!");
                    if (!target.isAlive()) {
                        System.out.println(target.getName() + " fainted!");
                    }
                } else {
                    System.out.println(target.getName() + " dodged the attack!");
                }
            }
        }
    }


    private void endRound() {
        for (Hero hero : heroes) {
            if (hero.isAlive()) {
                hero.heal((int) (hero.getMaxHp() * 0.1));
                hero.restoreMana((int) (hero.getMaxMp() * 0.1));
            }
        }
        removeDefeatedMonsters();
    }

    private void removeDefeatedMonsters() {
        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            if (!monster.isAlive()) {
                System.out.println(monster.getName() + " has been defeated!");
                iterator.remove();
            }
        }
    }

    private void endBattle() {
        heroesWon = !areAllHeroesFainted();
        if (heroesWon) {
            System.out.println("Heroes won the battle!");
            distributeRewards();
        } else {
            System.out.println("Heroes were defeated...");
        }
    }

    private void distributeRewards() {
        int totalExperience = monsters.size() * 2;
        int totalGold = 0;
        for (Monster monster : monsters) {
            totalGold += monster.getLevel() * 100;
        }

        for (Hero hero : heroes) {
            if (hero.isAlive()) {
                hero.gainExperience(totalExperience);
                hero.gainGold(totalGold);
            } else {
                hero.revive();
            }
        }
    }

    private boolean areAllHeroesFainted() {
        return heroes.stream().noneMatch(Hero::isAlive);
    }

    private boolean areAllMonstersDead() {
        return monsters.isEmpty();
    }

    private void showBattleInfo() {
        System.out.println("\nHeroes:");
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
        System.out.println("\nMonsters:");
        for (Monster monster : monsters) {
            System.out.println(monster);
        }
    }

    public boolean isHeroesWon() {
        return heroesWon;
    }
}
