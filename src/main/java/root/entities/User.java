package root.entities;

public class User {
	private long id;
    private String name;
    private String image;
    private int score;
    private int active;
    private String userName;
    private String passWord;
    private String role;
    private int levelId;
    private String level;
    
    
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public User(long id, String name, String image, int score, int active) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.score = score;
		this.active = active;
	}
	public User() {
		
	}
    
	public User(long id, String name, String image, int score, int active, String userName, String passWord,
			String role, int levelId, String level) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.score = score;
		this.active = active;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
		this.levelId = levelId;
		this.level = level;
	}
	public User(long id, String userName, String passWord, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}
	
	
}
