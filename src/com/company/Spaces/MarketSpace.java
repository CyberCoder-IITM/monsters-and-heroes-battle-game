package com.company.Spaces;


import com.company.Characters.Heroes.Hero;
import com.company.Market;

public class MarketSpace extends Space {
    private Market market;

    public MarketSpace() {
        super(true, true);  // MarketSpace is accessible and has a market
        this.market = new Market();
    }

    @Override
    public String getSymbol() {
        return "M";  // 'M' for Market
    }

    @Override
    public void onEnter(Hero hero) {
        System.out.println("You've entered a market space. Type 'M' to enter the market.");
    }

    public Market getMarket() {
        return market;
    }

    public void enterMarket(Hero hero) {
        System.out.println("Welcome to the market, " + hero.getName() + "!");
        market.enter(hero);
    }
}