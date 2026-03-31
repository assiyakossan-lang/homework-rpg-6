package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;
import java.util.Random;

public class DodgeHandler extends DefenseHandler {

    private double dodgeChance;
    private Random random = new Random();

    public DodgeHandler(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    @Override
    public void handle(int damage, ArenaFighter target) {
        double roll = random.nextDouble();

        if (roll < dodgeChance) {
            System.out.println("Dodged!");
            return; // СТОП цепочка
        }

        passToNext(damage, target);
    }
}