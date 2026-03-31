package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class ArmorHandler extends DefenseHandler {

    private int armor;

    public ArmorHandler(int armor) {
        this.armor = armor;
    }

    @Override
    public void handle(int damage, ArenaFighter target) {
        int reduced = Math.max(0, damage - armor);
        passToNext(reduced, target);
    }
}
