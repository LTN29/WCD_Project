package root.entities;

public class StoryType {
    private int id;
    private String title;
    private Integer active;

    public StoryType() {}

    public StoryType(int id, String title, Integer active) {
        this.id = id;
        this.title = title;
        this.active = active;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}