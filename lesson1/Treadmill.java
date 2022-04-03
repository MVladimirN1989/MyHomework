package lesson1;

public class Treadmill implements Obstacle{
    private Integer length;

    public Treadmill(Integer length) {
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    @Override
    public void pass(Member member) {
member.run(this);
    }
}
