package root.entities;

public class Story {
	private int id;
    private String title;
    private int chapterNumber;
    private String introduction;
    private String image;
    private int likeNumber;
    private int followNumber;
    private long viewNumber;
    private int authorId;
    private int statusId;
    private int categoryId;
    private int storyTypeId;
    private int scheduleId;
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
	public int getChapterNumber() {
		return chapterNumber;
	}
	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLikeNumber() {
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}
	public int getFollowNumber() {
		return followNumber;
	}
	public void setFollowNumber(int followNumber) {
		this.followNumber = followNumber;
	}
	public long getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(long viewNumber) {
		this.viewNumber = viewNumber;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getStoryTypeId() {
		return storyTypeId;
	}
	public void setStoryTypeId(int storyTypeId) {
		this.storyTypeId = storyTypeId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Story(int id, String title, int chapterNumber, String introduction, String image, int likeNumber,
			int followNumber, long viewNumber, int authorId, int statusId, int categoryId, int storyTypeId,
			int scheduleId) {
		super();
		this.id = id;
		this.title = title;
		this.chapterNumber = chapterNumber;
		this.introduction = introduction;
		this.image = image;
		this.likeNumber = likeNumber;
		this.followNumber = followNumber;
		this.viewNumber = viewNumber;
		this.authorId = authorId;
		this.statusId = statusId;
		this.categoryId = categoryId;
		this.storyTypeId = storyTypeId;
		this.scheduleId = scheduleId;
	}
	public Story() {
	
	}

	
	
}
