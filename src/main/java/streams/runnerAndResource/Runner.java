package streams.runnerAndResource;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Runner {
    private HashMap<Integer, Resource> resources = new HashMap<Integer, Resource>();

    public Iterable<Resource> getResources() {
        return this.resources.values();
    }

    public Resource acquireResource(int id){
        Resource w = this.resources.getOrDefault(id, null);
        if (w == null){
            w = new Resource(id);
            this.resources.put(id, w);
        }
        return w;
    }

    public void releaseResource(int id) throws IllegalArgumentException {
        Resource w = this.resources.getOrDefault(id, null);
        if (w == null)
            throw new IllegalArgumentException();

        w.dispose();

    }

    public class Resource{
        private List<String> tasks = new LinkedList<String>();
        private int id;

        public Resource(int id) {
            this.id = id;
        }

        public Iterable<String> getTasks() {
            return this.tasks;
        }

        public int getId() {
            return id;
        }

        public void performTask(String task) throws IllegalArgumentException {
            if (this.tasks == null){
                throw new IllegalArgumentException(this.getClass().getName());
            }

            this.tasks.add(task);
        }

        public void dispose() {
            this.tasks.clear();
            this.tasks = null;
        }
    }

    public static void main(String[] args) {
        Runner d = new Runner();

        d.acquireResource(2).performTask("Task 21");
        System.out.println(String.join(", ", d.acquireResource(2).getTasks()));
        d.releaseResource(2);
        d.acquireResource(3).performTask("Task 101");
        System.out.println(String.join(", ", d.acquireResource(3).getTasks()));
        d.releaseResource(3);

    }
}
