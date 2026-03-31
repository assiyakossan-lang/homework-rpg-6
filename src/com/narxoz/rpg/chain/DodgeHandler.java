package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;
import java.util.Random;

public class DodgeHandler extends DefenseHandler {

    private double dodgeChance;
    private Random random;

    public DodgeHandler(double dodgeChance) {
        this.dodgeChance = dodgeChance;
        this.random = new Random();
    }

    public DodgeHandler(double dodgeChance, long seed) {
        this.dodgeChance = dodgeChance;
        this.random = new Random(seed);
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