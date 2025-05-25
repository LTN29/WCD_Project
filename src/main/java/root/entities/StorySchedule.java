package root.entities;

public class StorySchedule {
    private int id;
    private String day;
    private Integer time;

    public StorySchedule() {}

    public StorySchedule(int id, String day, Integer time) {
        this.id = id;
        this.day = day;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}