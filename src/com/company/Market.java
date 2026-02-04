package com.company;

import com.company.Characters.Heroes.Hero;
import com.company.Items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Market {
    private List<Item> items;
    private Random random;

    public Market() {
        items = new ArrayList<>();
        random = new Random();
        generateItems();
    }

    private void generateItems() {
        // Generate a random assortment of items
        // This is a simplified version; you might want to read from files for more variety
        for (int i = 0; i < 10; i++) {
            items.add(new Weapon("Sword" + i, 100 + random.nextInt(900), 1 + random.nextInt(5), 10 + random.nextInt(90), 1));
            items.add(new Armor("Shield" + i, 80 + random.nextInt(720), 1 + random.nextInt(5), 5 + random.nextInt(45)));
            items.add(new Potion("Health Potion" + i, 50 + random.nextInt(450), 1 + random.nextInt(5), 100 + random.nextInt(400)));
            items.add(new Spell("Fireball" + i, 200 + random.nextInt(800), 1 + random.nextInt(5), 20 + random.nextInt(80), 50 + random.nextInt(150), "Fire"));
        }
    }

    public void enter(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        boolean inMarket = true;

        while (inMarket) {
            System.out.println("\nWelcome to the market, " + hero.getName() + "!");
            System.out.println("Your gold: " + hero.getGold());
            System.out.println("1. Buy item");
            System.out.println("2. Sell item");
            System.out.println("3. Exit market");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    buyItem(hero);
                    break;
                case 2:
                    sellItem(hero);
                    break;
                case 3:
                    inMarket = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void buyItem(Hero hero) {
        System.out.println("\nItems for sale:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }

        System.out.print("Enter the number of the item you want to buy (0 to cancel): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= items.size()) {
            Item item = items.get(choice - 1);
            if (hero.canBuyItem(item)) {
                hero.buyItem(item);
                items.remove(item);
            } else {
                System.out.println("You can't afford this item or your level is too low.");
            }
        } else if (choice != 0) {
            System.out.println("Invalid choice.");
        }
    }

    private void sellItem(Hero hero) {
        List<Item> inventory = hero.getInventory();
        System.out.println("\nYour inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));
        }

        System.out.print("Enter the number of the item you want to sell (0 to cancel): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= inventory.size()) {
            Item item = inventory.get(choice - 1);
            hero.sellItem(item);
            items.add(item);
        } else if (choice != 0) {
            System.out.println("Invalid choice.");
        }
    }
}
