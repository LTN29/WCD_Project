package root.entities;

public class Status {
    private int id;
    private String title;
    private int active;
    private Integer groupStatusId;
    
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Integer getGroupStatusId() {
		return groupStatusId;
	}
	public void setGroupStatusId(Integer groupStatusId) {
		this.groupStatusId = groupStatusId;
	}
	public Status(int id, String title, int active, Integer groupStatusId) {
		super();
		this.id = id;
		this.title = title;
		this.active = active;
		this.groupStatusId = groupStatusId;
	}
	public Status() {
	
	}
  
	
	
}
