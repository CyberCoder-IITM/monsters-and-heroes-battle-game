package com.company.Spaces;


import com.company.Characters.Heroes.Hero;

public class CommonSpace extends Space {
    private boolean hasBattle;

    public CommonSpace() {
        super(true, false); // Common spaces are accessible but don't have markets
        this.hasBattle = false;
    }

    @Override
    public String getSymbol() {
        return "C"; // 'C' for Common
    }

    @Override
    public void onEnter(Hero hero) {
        // Check for battle chance
        if (Math.random() < 0.2) { // 20% chance of battle
            hasBattle = true;
            System.out.println("A battle has started!");
            // Trigger battle logic here
        } else {
            System.out.println("You enter a common space. It's peaceful... for now.");
        }
    }

    public boolean hasBattle() {
        return hasBattle;
    }

    public void resetBattle() {
        hasBattle = false;
    }
}