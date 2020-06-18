import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CreatingPersons {
    public static void main(String[] args) throws IOException {
        String folderPath="./src/resources/";
        String[] strings = {"DB 58 AA A9","40 82 AA A9","7B 1E 03 89"};
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Summons totems");
        courses.add("Cooking goblins");
        ArrayList<String> achievements = new ArrayList<>();
        achievements.add("GURU");
        achievements.add("Ohh");
        achievements.add("What");
        achievements.add("the duck");
        achievements.add("is that?");
        Person dPerson = new Person(strings[0],"Орк", "Шаман", courses, achievements, 8000);

        ArrayList<String> courses1 = new ArrayList<>();
        courses1.add("Blade hurricane");
        courses1.add("Defend them all");
        courses1.add("Shield bush");
        ArrayList<String> achievements1 = new ArrayList<>();
        achievements1.add("Defender");
        achievements1.add("Leader");
        achievements1.add("Dungeon crusher");
        achievements1.add("Shield Wall");
        Person dPerson1 = new Person(strings[1],"Хуман", "Паладин", courses1, achievements1, 7700);

        ArrayList<String> courses2 = new ArrayList<>();
        courses2.add("Herbal collection");
        courses2.add("Alchemist");
        courses2.add("Throwing bottles");
        ArrayList<String> achievements2 = new ArrayList<>();
        achievements2.add("Top PVE Heal");
        achievements2.add("Heal to Death");
        achievements2.add("Druid of North");
        achievements2.add("Mage as wizard, but better");
        Person dPerson2 = new Person(strings[2],"Эльфийка", "Прист", courses2, achievements2, 6000);

        TreeMap<String,Person> map = new TreeMap<>();
        map.put(strings[0],dPerson);
        map.put(strings[1],dPerson1);
        map.put(strings[2],dPerson2);


        for (Map.Entry<String,Person> pair:
             map.entrySet()) {
            FileOutputStream fos = new FileOutputStream(folderPath+pair.getKey()+".out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pair.getValue());
            oos.close();
        }
    }
}
