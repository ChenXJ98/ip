import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(Storage storage) throws FileNotFoundException, InvalidDateTimeException {
        this.list = storage.getList();
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    public void updateData(Storage storage) throws IOException {
        storage.updateFile(list);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(int num) {
        int index = num - 1;
        Task task = list.get(index);
        this.list.remove(index);
        return task;
    }

    public Task markDone(int num) {
        int index = num - 1;
        this.list.get(index).markAsDone();
        return this.list.get(index);
    }

    public int getNumOfTask() {
        return this.list.size();
    }

    private int getNumOfDoneTask() {
        int num = 0;
        for (Task task : list) {
            if (task.checkDone()) {
                num++;
            }
        }
        return num;
    }

    private String showList() {
        StringBuffer lst = new StringBuffer();
        lst.append("Here are the tasks in your list:\n");
        int listSize = getNumOfTask();
        for (int i = 0; i < listSize; i++) {
            lst.append((i + 1) + ". " + this.list.get(i).toString() + "\n");
        }
        return lst.toString();
    }

    @Override
    public String toString() {
        return showList();
    }


}
