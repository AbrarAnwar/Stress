import spark.*;
import spark.template.velocity.*;
import java.util.*;
import static spark.Spark.*;

public class TaskList {

    public static void main(String[] args) {

        exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
//        staticFiles.location("/public");
//        staticFileLocation("/public");
        port(9999);

        before("/*", (q, a) -> System.out.println("Received api call"));



        // Add new Task
        post("/todos/add", (req, res) -> {
            System.out.println("TEST");
            TaskDao.add(Task.create(req.queryParams("todo-title"),
                    req.queryParams("priority"), req.queryParams("date")));
            res.status(200);
            return "";
        });

        // Remove task
        delete("/todos/removeTask/:id", (req, res) -> {
            TaskDao.remove(req.params("id"));
            return true;
        });

        // alter title
        put("/todos/:id/title", (req, res) -> {
            TaskDao.update(req.params(":id"), req.queryParams("new-title"));
            return true;
        });

        // alter status
        put("/todos/status/:id", (req, res) -> {
            TaskDao.alterStatus(req.params(":id"));
            return TaskDao.find(req.params(":id")).getCompleted();
        });


        // get one task
//        get("/todos/task", (req, res) -> {
//            return TaskDao.find(req.params("todo-title").toString());
//        });

        get("/todos/:id/single", (req, res) -> TaskDao.find(req.params("id").toString()));



        // get tasks by priority
        get("/todos/all/:priority", (req, res) -> TaskDao.priority(Integer.parseInt(req.params("priority"))));

        // split by date
        get("/todos/all/:date", (req, res) -> (TaskDao.dateList((req.params("date")))));

    }



}
