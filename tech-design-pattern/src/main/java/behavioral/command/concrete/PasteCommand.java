package behavioral.command.concrete;

import behavioral.command.EditCommand;

public class PasteCommand implements EditCommand {

    @Override
    public void doCommand() {
        System.out.println("paste command");
    }

    @Override
    public void undoCommand() {
        System.out.println("unpaste command");
    }
}
