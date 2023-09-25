package oop;

public abstract class Literature {
    private String title;
    public abstract String  GetCard();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
