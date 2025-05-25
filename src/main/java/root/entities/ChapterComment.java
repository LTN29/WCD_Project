package root.entities;



public class ChapterComment {
    private long id;
    private String content;
    private Integer active;
    private Integer chapterId;
    private int userId;

    public ChapterComment() {}

    public ChapterComment(long id, String content, Integer active, Integer chapterId, int userId) {
        this.id = id;
        this.content = content;
        this.active = active;
        this.chapterId = chapterId;
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

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
