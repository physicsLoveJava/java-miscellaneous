package behavioral.command;

import behavioral.command.concrete.CopyCommand;
import behavioral.command.concrete.CopyLineCommand;
import behavioral.command.concrete.PasteCommand;
import behavioral.command.concrete.UpdateCommand;

import java.util.Random;
import java.util.Stack;

public class Invoker {

    private EditCommand[] cmdList;

    private Stack<EditCommand> doneList;

    public Invoker() {
        this.cmdList = new EditCommand[] {
                new CopyCommand(),
                new CopyLineCommand(),
                new PasteCommand(),
                new UpdateCommand()
        };
        this.doneList = new Stack<>();
    }

    public void randomRun() {
        int len = this.cmdList.length;
        EditCommand command = this.cmdList[new Random().nextInt(len)];
        doneList.push(command);
        command.doCommand();
    }

    public void undo() {
        if(!doneList.empty()) {
            doneList.pop().undoCommand();
        }
    }

    public void undoAll() {
        while(!doneList.empty()) {
            doneList.pop().undoCommand();
        }
    }
}
