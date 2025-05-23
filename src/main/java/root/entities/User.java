package root.entities;

public class User {
	private long id;
    private String name;
    private String image;
    private int score;
    private int active;
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
    
    
}
