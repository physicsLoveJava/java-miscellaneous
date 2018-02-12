package behavioral.command.concrete;

import behavioral.command.EditCommand;

public class CopyCommand implements EditCommand {

    @Override
    public void doCommand() {
        System.out.println("copy command");
    }

    @Override
    public void undoCommand() {
        System.out.println("uncopy command");
    }
}
