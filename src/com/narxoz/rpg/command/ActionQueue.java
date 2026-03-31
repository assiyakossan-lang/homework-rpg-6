package com.narxoz.rpg.command;
import java.util.*;

public class ActionQueue {

    private List<ActionCommand> commands = new ArrayList<>();

    public void enqueue(ActionCommand cmd) {
        commands.add(cmd);
    }

    public void undoLast() {
        if (!commands.isEmpty()) {
            commands.remove(commands.size() - 1);
        }
    }

    public void executeAll() {
        for (ActionCommand cmd : commands) {
            cmd.execute();
        }
        commands.clear();
    }

    public List<String> getCommandDescriptions() {
        List<String> list = new ArrayList<>();
        for (ActionCommand c : commands) {
            list.add(c.getDescription());
        }
        return list;
    }
}