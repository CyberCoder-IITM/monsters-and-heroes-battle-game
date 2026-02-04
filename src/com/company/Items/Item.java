package com.company.Items;
import com.company.Characters.Heroes.Hero;

public abstract class Item {
    protected String name;
    protected int price;
    protected int level;
    protected int remainingUses;

    public Item(String name, int price, int level, int remainingUses) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.remainingUses = remainingUses;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    public abstract void use(Hero hero);

    public void use() {
        if (remainingUses > 0) {
            remainingUses--;
        }
    }

    public boolean isUsable() {
        return remainingUses > 0;
    }

    public abstract String getItemType();


    @Override
    public String toString() {
        return String.format("%s (Level %d) - Price: %d gold, Uses left: %d", name, level, price, remainingUses);
    }
}