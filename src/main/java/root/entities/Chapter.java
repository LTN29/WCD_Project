package root.entities;

public class Chapter {
	private int id;
	private int storyId;
	private int number;
	private String title;
	private String content;

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

	public Chapter(int id, int i, int j, String dayCreate, String string) {
		super();
		this.id = id;
		this.title = i;
		this.content = j;
		this.dayCreate = dayCreate;
		this.storyId = string;
	}

	public Chapter() {

	}

}
