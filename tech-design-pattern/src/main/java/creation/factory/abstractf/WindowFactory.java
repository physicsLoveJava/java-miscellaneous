package creation.factory.abstractf;

public abstract class WindowFactory {

    abstract Window createWindow();

    abstract Window.Container createContainer();

    abstract Window.Button createButton();

    abstract Window.Modal createModal();

    abstract Window.Text createText();

    abstract Window.Select createSelect();

    abstract Window.List createList();

}
