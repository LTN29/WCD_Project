package root.entities;

public class LevelUser {
    private int id;
    private String level;
    private Integer scoreStart;
    private Integer scoreEnd;

    public LevelUser() {}

    public LevelUser(int id, String level, Integer scoreStart, Integer scoreEnd) {
        this.id = id;
        this.level = level;
        this.scoreStart = scoreStart;
        this.scoreEnd = scoreEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getScoreStart() {
        return scoreStart;
    }

    public void setScoreStart(Integer scoreStart) {
        this.scoreStart = scoreStart;
    }

    public Integer getScoreEnd() {
        return scoreEnd;
    }

    public void setScoreEnd(Integer scoreEnd) {
        this.scoreEnd = scoreEnd;
    }
}
