package root.entities;

public class ChapterImage {
    private long id;
    private String image;
    private Integer index;
    private Integer chapterId;

    public ChapterImage() {}

    public ChapterImage(long id, String image, Integer index, Integer chapterId) {
        this.id = id;
        this.image = image;
        this.index = index;
        this.chapterId = chapterId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }
}
