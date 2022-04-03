package lesson1;

public class Wall implements Obstacle{
    private Integer height;

    public Wall(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public void pass(Member member) {
        member.jump(this);
    }
}
