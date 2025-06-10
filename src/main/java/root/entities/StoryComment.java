package root.entities;

public class StoryComment {
    private long id;
    private String content;
    private Integer active;
    private int storyId;
    private long userId;
    private String userName;
    
    
    public StoryComment() {}

    public StoryComment(long id, String content, Integer active, int storyId, long userId2) {
        this.id = id;
        this.content = content;
        this.active = active;
        this.storyId = storyId;
        this.userId = userId2;
    }

    
    
    public StoryComment(int id, String content, Integer active, int storyId, long userId, String userName) {
		super();
		this.id = id;
		this.content = content;
		this.active = active;
		this.storyId = storyId;
		this.userId = userId;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
