package com.company.Spaces;


import com.company.Characters.Heroes.Hero;

public class InaccessibleSpace extends Space {
    public InaccessibleSpace() {
        super(false, false);  // InaccessibleSpace is not accessible and doesn't have a market
    }

    @Override
    public String getSymbol() {
        return "X";  // 'X' for Inaccessible
    }

    @Override
    public void onEnter(Hero hero) {
        // This method should never be called for InaccessibleSpace,
        // but we'll include an error message just in case
        System.out.println("Error: Cannot enter an inaccessible space!");
    }
}