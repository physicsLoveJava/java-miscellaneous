package behavioral.command.concrete;

import behavioral.command.EditCommand;

public class CopyLineCommand implements EditCommand{

    @Override
    public void doCommand() {
        System.out.println("copy one line command");
    }

    @Override
    public void undoCommand() {
        System.out.println("uncopy one line command");
    }
}
