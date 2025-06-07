package root.entities;

public class ChapterComment {
    private long id;
    private String content;
    private Integer active;
    private Integer chapterId;
    private long userId;
    private String userName; 
    private String chapterTitle;
    
    public ChapterComment() {}

    
    public ChapterComment(long id, String content, Integer active, Integer chapterId, long userId, String userName,
			String chapterTitle) {
		super();
		this.id = id;
		this.content = content;
		this.active = active;
		this.chapterId = chapterId;
		this.userId = userId;
		this.userName = userName;
		this.chapterTitle = chapterTitle;
	}


	public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getActive() { return active; }
    public void setActive(Integer active) { this.active = active; }
    public Integer getChapterId() { return chapterId; }
    public void setChapterId(Integer chapterId) { this.chapterId = chapterId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
	public String getChapterTitle() {return chapterTitle;}
	public void setChapterTitle(String chapterTitle) {this.chapterTitle = chapterTitle;}
    
}
