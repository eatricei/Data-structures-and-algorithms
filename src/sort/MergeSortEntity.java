package sort;

/**
 * Created by swj on 2017/12/25.
 */
public class MergeSortEntity implements Comparable<MergeSortEntity>{

    private String name;
    private int age;
    private int score;

    public MergeSortEntity(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public MergeSortEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(MergeSortEntity o) {
        return this.score - o.score;
    }

    @Override
    public String toString() {
        return "MergeSortEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
