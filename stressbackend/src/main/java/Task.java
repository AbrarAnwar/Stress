import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import lombok.*;


@Data
//@AllArgsConstructor
public class Task implements Comparable{

    String title;
    int priority;
    Date date;
    int completed;
    String id;
    public Task(String title, int priority, Date date, int completed, String id) {
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.completed = completed;
        this.id = id;
        System.out.println(id);
    }


    public void alterStatus() {
        if(completed == 0 || completed == -1) {
            completed = 1;
        }
        else if(date.compareTo(new Date()) == -1 && completed == 1) {
            completed = -1;
        }
        else
            completed = 0;
    }

    public boolean isComplete() {
        return this.completed == 1;
    }

    public static Task create(String title, String priority, String date) throws ParseException{
        return new Task(title, Integer.parseInt(priority),
                new SimpleDateFormat("dd/MM/yyyy").parse(date), 0
        , UUID.randomUUID().toString());
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String t) {
        title = t;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int p) {
        priority = p;
    }
    public String getID() {
        return id;
    }
    public int getCompleted() {
        return completed;
    }

    public Date getDate() {
        return date;
    }

    public int compareTo(Object o) {
        return this.title.compareTo(((Task)o).title);
    }
    public boolean equals(Object o) {
        return this.title.equals(((Task)o).title);
    }
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

        return title;
    }


}