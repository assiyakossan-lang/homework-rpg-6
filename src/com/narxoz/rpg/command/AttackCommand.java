package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaOpponent;

public class AttackCommand implements ActionCommand {

    private final ArenaOpponent target;
    private final int damage;
    private int damageDealt;

    public AttackCommand(ArenaOpponent target, int damage) {
        this.target = target;
        this.damage = damage;
    }

    @Override
    public void execute() {
        int before = target.getHealth();
        target.takeDamage(damage);
        damageDealt = before - target.getHealth();
    }

    @Override
    public void undo() {
        target.restoreHealth(damageDealt);
    }

    @Override
    public String getDescription() {
        return "Attack for " + damage;
    }
}
