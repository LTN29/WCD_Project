package root.entities;

import java.sql.Date;

public class StoryLike {
    private long id;           
    private int userId;       
    private int storyId;       
    private Date dayCreate;    
    private int like;         

    public StoryLike() {}

    public StoryLike(long id, int userId, int storyId, Date dayCreate, int like) {
        this.id = id;
        this.userId = userId;
        this.storyId = storyId;
        this.dayCreate = dayCreate;
        this.like = like;
    }

    public StoryLike(int userId, int storyId, Date dayCreate, int like) {
        this.userId = userId;
        this.storyId = storyId;
        this.dayCreate = dayCreate;
        this.like = like;
    }

    // Getters và Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public Date getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(Date dayCreate) {
        this.dayCreate = dayCreate;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
