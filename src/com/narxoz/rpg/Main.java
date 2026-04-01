package com.narxoz.rpg;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.arena.TournamentResult;
import com.narxoz.rpg.chain.ArmorHandler;
import com.narxoz.rpg.chain.BlockHandler;
import com.narxoz.rpg.chain.DefenseHandler;
import com.narxoz.rpg.chain.DodgeHandler;
import com.narxoz.rpg.chain.HpHandler;
import com.narxoz.rpg.command.ActionQueue;
import com.narxoz.rpg.command.AttackCommand;
import com.narxoz.rpg.command.DefendCommand;
import com.narxoz.rpg.command.HealCommand;
import com.narxoz.rpg.tournament.TournamentEngine;

public class Main {

    public static void main(String[] args) {

        ArenaFighter hero = new ArenaFighter("Hero", 100, 0.20, 10, 5, 18, 3);
        ArenaOpponent enemy = new ArenaOpponent("Enemy", 80, 15);

        ActionQueue queue = new ActionQueue();
        queue.enqueue(new AttackCommand(enemy, hero.getAttackPower()));
        queue.enqueue(new HealCommand(hero, 10));
        queue.enqueue(new DefendCommand(hero, 0.1));

        System.out.println(queue.getCommandDescriptions());
        queue.undoLast();
        System.out.println(queue.getCommandDescriptions());
        queue.executeAll();

        DefenseHandler chain =
                new DodgeHandler(hero.getDodgeChance())
                .setNext(new BlockHandler(0.2))
                .setNext(new ArmorHandler(5))
                .setNext(new HpHandler());

        System.out.println("HP before: " + hero.getHealth());
        chain.handle(30, hero);
        System.out.println("HP after: " + hero.getHealth());

      
        TournamentEngine engine = new TournamentEngine();
        TournamentResult result = engine.runTournament(hero, enemy);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println(result.getLog());
    }
}