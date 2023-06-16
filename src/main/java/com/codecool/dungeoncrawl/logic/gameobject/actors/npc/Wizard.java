package com.codecool.dungeoncrawl.logic.gameobject.actors.npc;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.exceptions.NewLevelException;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.messages.Message;
import com.codecool.dungeoncrawl.logic.tasks.Journal;
import com.codecool.dungeoncrawl.logic.tasks.Task;


public class Wizard extends Npc {

    public Wizard(Cell cell) {
        super(cell);
    }

    @Override
    public void action(GameObject gameObject) throws NewLevelException {
        if (gameObject instanceof Player) {
            Player player = (Player) gameObject;
            Message.getInstance().setActualMessage("You met an old wizard");
            Message.getInstance().setActualMessage("He won't let you go unless you bring him 50 gold");
            System.out.println("You met an old wizard");
            System.out.println("He won't let you go unless you bring him 50 gold");

            createWizardTask(player);

            if(player.getInventory().getGoldAmount() >= 50) {
                Message.getInstance().setActualMessage("Wizard kindly lets you in...");
                System.out.println("Wizard kindly lets you in...");
                this.getCell().setGameObject(null);

                Task task = player.getJournal().moveTaskToFinishedList("Gold for the Wizz");

                int expReward = task.getExpReward();
                System.out.println("Task finished! You gained " + expReward + " exp!");

                player.setExperience(player.getExperience() + expReward);
            }
            else {
                Message.getInstance().setActualMessage("Bring more gold!");
                System.out.println("Bring more gold!");
            }
        }
    }

    public void createWizardTask(Player player) {
        Journal journal = player.getJournal();

        if(journal.taskAlreadyInJournal("Gold for the Wizz")) {
            return;
        }

        Task task = new Task("Gold for the Wizz", "Bring 50 gold to the wizard", 100);

        journal.addTaskToTheJournal(task);
        System.out.println("***  New task Added ***");
        System.out.println(task.getName());
        System.out.println();
    }

    @Override
    public String getTileName() {
        return "wizard";
    }
}
