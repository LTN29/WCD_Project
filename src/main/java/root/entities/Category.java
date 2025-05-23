package root.entities;

public class Category {
	private int _id;
	private String _name;
	private boolean _active;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public boolean is_active() {
		return _active;
	}
	public void set_active(boolean _active) {
		this._active = _active;
	}
	public Category(int _id, String _name, boolean _active) {
		super();
		this._id = _id;
		this._name = _name;
		this._active = _active;
	}
	public Category() {
	}
	
	
	
}
