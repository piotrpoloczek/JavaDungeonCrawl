package com.codecool.dungeoncrawl.logic.gameobject.actors.npc;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.GameException;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.gameobject.items.Item;
import com.codecool.dungeoncrawl.logic.tasks.Journal;
import com.codecool.dungeoncrawl.logic.tasks.Task;


public class Princes extends Npc {

    public Princes(Cell cell) {
        super(cell);
    }

    @Override
    public void action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            System.out.println("You found an princes");
            System.out.println("You need ring");

            createPrincesTask(player);

            Item crown = player.getInventory().getSack().stream()
                    .filter(item1 -> "crown".equals(item1.getName()))
                    .findAny()
                    .orElse(null);

            if(player.getInventory().isInABag(crown)) {
                System.out.println("You have become king! Happy end!");
                this.getCell().setGameObject(null);

                Task task = player.getJournal().moveTaskToFinishedList("Crown for the princes");

                int expReward = task.getExpReward();
                System.out.println("Task finished! You gained " + expReward + " exp!");

                player.setExperience(player.getExperience() + expReward);
            }
            else {
                System.out.println("Find the crown!");
            }
        }
    }

    public void createPrincesTask(Player player) {
        Journal journal = player.getJournal();

        if(journal.taskAlreadyInJournal("Crown for the princes")) {
            return;
        }

        Task task = new Task("Crown for the princes", "Bring crown to the princes", 1000);

        journal.addTaskToTheJournal(task);
        System.out.println("***  New task Added ***");
        System.out.println(task.getName());
        System.out.println();
    }

    @Override
    public String getTileName() {
        return "princes";
    }
}
