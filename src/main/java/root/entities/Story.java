package root.entities;

public class Story {
	private int id;
	private String title;
	private int chapterNumber;
	private String introduction;
	private String image;
	private int likeNumber;
	private int followNumber;
	private int viewNumber;
	private int authorId;
	private int statusId;
	private int categoryId;



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

	public int getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(int viewNumber) {
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

	public Story(int id, String title, int chapterNumber, String introduction, String image, int likeNumber,
			int followNumber, int viewNumber, int authorId, int statusId, int categoryId) {
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
	}

	public Story() {

	}

}
