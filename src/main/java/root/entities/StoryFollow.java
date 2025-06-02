package root.entities;

import java.sql.Date;

public class StoryFollow {
    private long id;
    private long userId;
    private Integer storyId;
    private Date dayCreate;
    private Integer follow;

    public StoryFollow() {}

    public StoryFollow(long id, long userId, Integer storyId, Date dayCreate, Integer follow) {
        this.id = id;
        this.userId = userId;
        this.storyId = storyId;
        this.dayCreate = dayCreate;
        this.follow = follow;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Date getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(Date dayCreate) {
        this.dayCreate = dayCreate;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }
}
