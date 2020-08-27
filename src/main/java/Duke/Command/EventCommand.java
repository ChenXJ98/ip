package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

public class EventCommand extends Command {
    public String input;

    public EventCommand(String input) throws NullEventInputException {
        if (input.length() == 0) {
            throw new NullEventInputException();
        }
        this.input = input;
    }

    private String setDate(String date) throws InvalidDateTimeException {
        String[] split = date.split("/");
        if (date.length() != 10 || split.length != 3) {
            throw new InvalidDateTimeException();
        }
        String formatDate = split[0] + "-" + split[1] + "-" + split[2];
        return formatDate;
    }

    private String setTime(String time) throws InvalidDateTimeException {
        if (time.length() != 4) {
            throw new InvalidDateTimeException();
        }
        return time.substring(0, 2) + ":" + time.substring(2);
    }

    private Event createEvent(String description, String datetime) throws InvalidDateTimeException {
        String[] datetimeArray = datetime.split(" ");

        if (datetimeArray.length != 2) {
            throw new InvalidDateTimeException();
        }
        String date = setDate(datetimeArray[0]);
        String time = setTime(datetimeArray[1]);
        return new Event(description, date, time);
    }

    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidEventInputException, InvalidDateTimeException, IOException {
        String[] eventTaskArray = input.split(" /at ");
        if (eventTaskArray.length != 2) {
            throw new InvalidEventInputException();
        }
        String eventDescription = eventTaskArray[0];
        String at = eventTaskArray[1];
        Event event = createEvent(eventDescription, at);
        tasks.addTask(event);
        tasks.updateData(storage);
        ui.showAdd(event, tasks);
    }
}