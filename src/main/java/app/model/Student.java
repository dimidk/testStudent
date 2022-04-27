package app.model;

public class Student {

    private  int id;
    private String fullName;
    private String cardID;
    private int semester;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id;}

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
