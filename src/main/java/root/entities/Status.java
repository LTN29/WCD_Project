package root.entities;

public class Status {
    private int id;
    private String title;

  
    public Status() {}

    public Status(int id, String title) {
        this.id = id;
        this.title = title;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
