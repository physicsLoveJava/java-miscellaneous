package behavioral.command.concrete;

import behavioral.command.EditCommand;

public class UpdateCommand implements EditCommand {
    @Override
    public void doCommand() {
        System.out.println("update command");
    }

    @Override
    public void undoCommand() {
        System.out.println("can't update command");
    }
}
