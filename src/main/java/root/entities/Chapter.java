package root.entities;

public class Chapter {
	private int id;
    private String title;
    private String content;
    private String dayCreate;
    private int storyId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDayCreate() {
		return dayCreate;
	}
	public void setDayCreate(String dayCreate) {
		this.dayCreate = dayCreate;
	}
	public int getStoryId() {
		return storyId;
	}
	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}
	public Chapter(int id, String title, String content, String dayCreate, int storyId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.dayCreate = dayCreate;
		this.storyId = storyId;
	}
	public Chapter() {
		
	}
    
    
}
