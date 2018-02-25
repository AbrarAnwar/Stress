
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;

public class TaskDao {

    private static final List<Task> DATA = new ArrayList<Task>();

    public static void add(Task task) {
        DATA.add(task);
    }

    public static Task find(String id) {
        for(Task t : DATA) {
            if(t.getID().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public static void update(String id, String newTitle) {
        find(id).setTitle(newTitle);
    }



    public static void remove(String id) {
        DATA.remove(find(id));
    }

    public static void alterStatus(String id) {
        find(id).alterStatus();
    }

    public static List<Task> all() {
        return DATA;
    }

    public static String priority(int c) {
        List<Task> result = new ArrayList<Task>();
        for(Task t : DATA) {
            if(t.getPriority() == c &&  new SimpleDateFormat("MM/dd/yyyy").format(t.getDate()).equals( new SimpleDateFormat("MM/dd/yyyy").format(new Date()))) {
                result.add(t);
            }
        }
        return result.toString();
    }

    // String date should be in MM/DD/YYYY form
    public static String dateList(String date) {
        List<Task> result = new ArrayList<Task>();
        try {
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (Exception e) {

        }
        for(Task t : DATA) {
            if(t.getDate().equals(date)) {
                result.add(t);
            }
        }
        return result.toString();
    }
}