public class ExitCommand extends Command {
    @Override
    public boolean isExited() {
        return true;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
