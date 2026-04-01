package com.narxoz.rpg.tournament;

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
import java.util.Random;

public class TournamentEngine {

    public TournamentResult runTournament(ArenaFighter hero, ArenaOpponent enemy) {

        TournamentResult result = new TournamentResult();

        DefenseHandler chain =
                new DodgeHandler(hero.getDodgeChance())
                .setNext(new BlockHandler(hero.getBlockRating() / 100.0))
                .setNext(new ArmorHandler(hero.getArmorValue()))
                .setNext(new HpHandler());

        int round = 0;
        int maxRounds = 20;

        while (hero.isAlive() && enemy.isAlive() && round < maxRounds) {
            round++;
            result.addLine("Round " + round);

            ActionQueue queue = new ActionQueue();
            queue.enqueue(new AttackCommand(enemy, hero.getAttackPower()));
            queue.enqueue(new HealCommand(hero, 15));
            queue.enqueue(new DefendCommand(hero, 0.1));

            result.addLine(queue.getCommandDescriptions().toString());
            queue.executeAll();

            if (enemy.isAlive()) {
                chain.handle(enemy.getAttackPower(), hero);
            }

            result.addLine(String.format("Opponent HP: %d | Hero HP: %d", enemy.getHealth(), hero.getHealth()));
        }

        if (hero.isAlive()) result.setWinner(hero.getName());
        else result.setWinner(enemy.getName());

        result.setRounds(round);

        return result;
    }
}