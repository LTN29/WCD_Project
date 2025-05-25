package root.entities;

public class StoryComment {
    private int id;
    private String content;
    private Integer active;
    private int storyId;
    private int userId;

    public StoryComment() {}

    public StoryComment(int id, String content, Integer active, int storyId, int userId) {
        this.id = id;
        this.content = content;
        this.active = active;
        this.storyId = storyId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
