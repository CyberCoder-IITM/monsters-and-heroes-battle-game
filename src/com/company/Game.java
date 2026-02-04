package com.company;

import com.company.Characters.Heroes.Hero;
import com.company.Characters.Monsters.Monster;
import com.company.Factory.CharacterFactory;
import com.company.Factory.HeroFactory;
import com.company.Factory.MonsterFactory;
import com.company.Items.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private World world;
    private List<Hero> heroes;
    private boolean isRunning;
    private Scanner scanner;
    private CharacterFactory heroFactory;
    private CharacterFactory monsterFactory;

    public Game() {
        world = new World(8, 8); // 8x8 grid
        heroes = new ArrayList<>();
        isRunning = false;
        scanner = new Scanner(System.in);
        heroFactory = new HeroFactory();
        monsterFactory = new MonsterFactory();
    }

    public void start() {
        isRunning = true;
        initializeHeroes();
        while (isRunning) {
            displayWorld();
            handlePlayerInput();
            checkForBattleOrMarket();
        }
    }

    private void initializeHeroes() {
        InputHandler<Integer> inputHandler = new InputHandler<>(Integer.class, scanner);
        int partySize = inputHandler.getInput("How many heroes in your party? (1-3): ", 1, 3);

        for (int i = 0; i < partySize; i++) {
            String name = null;

            // Prompt for a valid hero name
            while (name == null || name.trim().isEmpty()) {
                System.out.println("Enter hero name:");
                name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Invalid hero name. Please enter a non-empty name.");
                }
            }

            Hero hero = null;

            // Prompt for a valid hero type
            while (hero == null) {
                System.out.println("Choose hero type (Warrior/Sorcerer/Paladin):");
                String type = scanner.nextLine().trim().toLowerCase();

                try {
                    hero = ((HeroFactory) heroFactory).createSpecificHero(name, type);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid hero type. Please try again.");
                }
            }

            Position startPosition = world.findValidStartPosition();
            hero.move(startPosition);
            heroes.add(hero);
        }
    }


    private void displayWorld() {
        world.displayWithHeroes(heroes);
    }

    private void handlePlayerInput() {
        System.out.println("Enter move (W/A/S/D) or action (I/M/Q):");
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "W":
            case "A":
            case "S":
            case "D":
                moveHeroes(input);
                break;
            case "I":
                displayHeroInfo();
                break;
            case "M":
                enterMarket();
                break;
            case "Q":
                isRunning = false;
                break;
            default:
                System.out.println("Invalid input. Try again.");
        }
    }

    private void moveHeroes(String direction) {
        int dx = 0, dy = 0;
        switch (direction) {
            case "W": dy = -1; break;
            case "S": dy = 1; break;
            case "A": dx = -1; break;
            case "D": dx = 1; break;
        }
        boolean canMove = true;
        for (Hero hero : heroes) {
            Position newPos = new Position(hero.getPosition().x + dx, hero.getPosition().y + dy);
            if (!world.isValidMove(newPos)) {
                canMove = false;
                break;
            }
        }
        if (canMove) {
            for (Hero hero : heroes) {
                Position newPos = new Position(hero.getPosition().x + dx, hero.getPosition().y + dy);
                hero.move(newPos);
                System.out.println(hero.getName() + " moved " + direction);
            }
        } else {
            System.out.println("Cannot move in that direction.");
        }
    }

    private void displayHeroInfo() {
        for (Hero hero : heroes) {
            System.out.println(hero.getInfo());
        }
    }

    private void enterMarket() {
        if (world.isMarketSpace(heroes.get(0).getPosition())) {
            Market market = new Market();
            for (Hero hero : heroes) {
                market.enter(hero);
            }
        } else {
            System.out.println("No market here. Move to a market space to enter the market.");
        }
    }

    private void checkForBattleOrMarket() {
        if (world.isCommonSpace(heroes.get(0).getPosition())) {
            if (new Random().nextDouble() < 0.2) { // 20% chance of battle
                startBattle();
            }
        }
    }

    private void startBattle() {
        List<Monster> monsters = generateMonsters();
        Battle battle = new Battle(heroes, monsters);
        battle.start();
        if (battle.isHeroesWon()) {
            distributeRewards();
        } else {
            gameOver();
        }
    }

    private List<Monster> generateMonsters() {
        List<Monster> monsters = new ArrayList<>();
        int highestHeroLevel = heroes.stream().mapToInt(Hero::getLevel).max().orElse(1);
        for (int i = 0; i < heroes.size(); i++) {
            Monster monster = (Monster) monsterFactory.createCharacter("Monster " + (i + 1), highestHeroLevel);
            monsters.add(monster);
        }
        return monsters;
    }

    private void distributeRewards() {
        int totalExperience = heroes.size() * 2;
        int totalGold = 0;
        for (Hero hero : heroes) {
            if (hero.isAlive()) {
                hero.gainExperience(totalExperience);
                int goldGain = hero.getLevel() * 100;
                hero.gainGold(goldGain);
                totalGold += goldGain;
            }
        }
        System.out.println("Heroes gained " + totalExperience + " experience and " + totalGold + " gold!");
    }

    public boolean canUpgradeWeapon(Hero hero, Weapon weapon) {
        // Define the cost of upgrading a weapon
        int upgradeCost = weapon.getLevel() * 100; // Example cost formula

        // Check if hero has enough gold and meets level requirements
        return hero.getGold() >= upgradeCost && hero.getLevel() >= weapon.getLevel();
    }
    public void openUpgradeMenu(Hero hero) {
        List<Weapon> weapons = hero.getWeapons();

        if (weapons.isEmpty()) {
            System.out.println("No weapons available for upgrade.");
            return;
        }

        System.out.println("Choose a weapon to upgrade:");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ". " + weapons.get(i));
        }

        int choice = scanner.nextInt() - 1; // Adjust for zero-indexing
        scanner.nextLine(); // Consume newline

        if (choice >= 0 && choice < weapons.size()) {
            Weapon selectedWeapon = weapons.get(choice);
            upgradeWeapon(hero, selectedWeapon);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void upgradeWeapon(Hero hero, Weapon weapon) {
        if (canUpgradeWeapon(hero, weapon)) {
            hero.reduceGold(weapon.getLevel() * 100); // Deduct gold
            weapon.upgrade(); // Upgrade the weapon
            System.out.println(hero.getName() + " upgraded " + weapon.getName() + "!");
        } else {
            System.out.println(hero.getName() + " cannot upgrade " + weapon.getName() + ".");
        }
    }

    private void gameOver() {
        System.out.println("Game Over! All heroes have been defeated.");
        isRunning = false;
    }
}
