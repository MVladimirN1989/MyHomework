package lesson1;

public class Cat implements Member{
    private Integer runLim;
    private Integer jumpLim;
    private String name;

    public Cat(Integer runLim, Integer jumpLim, String name) {
        this.runLim = runLim;
        this.jumpLim = jumpLim;
        this.name = name;
    }

    @Override
    public void run(Treadmill treadmill) {
        if (runLim<treadmill.getLength()){
            System.out.println(name + "не смог пробежать "+ runLim+ " метров");
        }
        System.out.println(name+" пробежал "+ runLim+ " метров");
    }

    @Override
    public void jump(Wall wall) {
        if (jumpLim<wall.getHeight()){
            System.out.println(name +" не прыгнул "+jumpLim + " сантиметров");
        }
        System.out.println(name+ " перепрыгнул "+jumpLim + " сантиметров");
    }
}
