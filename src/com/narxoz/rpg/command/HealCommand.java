package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;
public class HealCommand implements ActionCommand {

    private ArenaFighter hero;
    private int healAmount;
    private int actualHeal;

    public HealCommand(ArenaFighter hero, int healAmount) {
        this.hero = hero;
        this.healAmount = healAmount;
    }

    @Override
    public void execute() {
        int before = hero.getHealth();
        hero.heal(healAmount);
        actualHeal = hero.getHealth() - before;
    }

    @Override
    public void undo() {
        hero.takeDamage(actualHeal);
    }

    @Override
    public String getDescription() {
        return "Heal " + healAmount;
    }
}
