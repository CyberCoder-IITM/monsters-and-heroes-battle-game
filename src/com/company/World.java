package com.company;


import com.company.Characters.Heroes.Hero;
import com.company.Spaces.CommonSpace;
import com.company.Spaces.InaccessibleSpace;
import com.company.Spaces.MarketSpace;
import com.company.Spaces.Space;

import java.util.List;
import java.util.Random;

public class World {
    private Space[][] grid;
    private int width;
    private int height;
    private Random random;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Space[height][width];
        this.random = new Random();
        generateWorld();
    }

    public Position findValidStartPosition() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (isValidMove(x, y)) {
                return new Position(x, y);
            }
        }
    }

    private void generateWorld() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double chance = random.nextDouble();
                if (chance < 0.2) {
                    grid[i][j] = new InaccessibleSpace();
                } else if (chance < 0.5) {
                    grid[i][j] = new MarketSpace();
                } else {
                    grid[i][j] = new CommonSpace();
                }
            }
        }
    }


    public void displayWithHeroes(List<Hero> heroes) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean heroPresent = false;
                for (int i = 0; i < heroes.size(); i++) {
                    Hero hero = heroes.get(i);
                    if (hero.getPosition().x == x && hero.getPosition().y == y) {
                        System.out.print(hero.getName().substring(0, 2).toUpperCase() + " ");
                        heroPresent = true;
                        break;
                    }
                }
                if (!heroPresent) {
                    System.out.print(grid[y][x].getSymbol() + "  ");
                }
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height && grid[y][x].isAccessible();
    }


    public boolean isValidMove(Position pos) {
        return pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height && grid[pos.y][pos.x].isAccessible();
    }


    public boolean isMarketSpace(Position pos) {
        return grid[pos.y][pos.x] instanceof MarketSpace;
    }

    public boolean isCommonSpace(Position pos) {
        return grid[pos.y][pos.x] instanceof CommonSpace;
    }

    public Space getSpace(int x, int y) {
        if (isValidMove(x, y)) {
            return grid[y][x];
        }
        return null;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}