package src.com.nsu.rds.data.models;

public class Courses {
    private String initial;
    private String name;
    private int credit;

    public Courses(String initial, String name, int credit) {
        this.initial = initial;
        this.name = name;
        this.credit = credit;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
