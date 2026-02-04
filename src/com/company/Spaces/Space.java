package com.company.Spaces;

import com.company.Characters.Heroes.Hero;

public abstract class Space {
    protected boolean isAccessible;
    protected boolean hasMarket;

    public Space(boolean isAccessible, boolean hasMarket) {
        this.isAccessible = isAccessible;
        this.hasMarket = hasMarket;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public boolean hasMarket() {
        return hasMarket;
    }

    public abstract String getSymbol();

    public abstract void onEnter(Hero hero);
}
