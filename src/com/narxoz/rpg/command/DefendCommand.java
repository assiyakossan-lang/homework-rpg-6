package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class DefendCommand implements ActionCommand {

    private ArenaFighter hero;
    private double boost;

    public DefendCommand(ArenaFighter hero, double boost) {
        this.hero = hero;
        this.boost = boost;
    }

    @Override
    public void execute() {
        hero.modifyDodgeChance(boost);
    }

    @Override
    public void undo() {
        hero.modifyDodgeChance(-boost);
    }

    @Override
    public String getDescription() {
        return "Boost dodge " + boost;
    }
}