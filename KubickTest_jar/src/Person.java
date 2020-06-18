import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String uid;
    private String firstName;
    private String lastName;
    private ArrayList<String> courses;
    private ArrayList<String> achievements;

    private int exp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Person(String uid, String firstName, String lastName, ArrayList<String> courses, ArrayList<String> achievements, int exp) {
        this.uid=uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
        this.achievements = achievements;
        this.exp = exp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<String> achievements) {
        this.achievements = achievements;
    }


    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    public void addExp(){this.exp+=10;}
}
