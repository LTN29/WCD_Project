package root.entities;

import java.sql.Date;

public class Chapter {
    private int id;
    private String title;
    private String content;
    private Date dayCreate;
    private int storyId;

    public Chapter() {}

    public Chapter(int id, String title, String content, Date dayCreate, int storyId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dayCreate = dayCreate;
        this.storyId = storyId;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getDayCreate() { return dayCreate; }
    public void setDayCreate(Date dayCreate) { this.dayCreate = dayCreate; }

    public int getStoryId() { return storyId; }
    public void setStoryId(int storyId) { this.storyId = storyId; }
}
