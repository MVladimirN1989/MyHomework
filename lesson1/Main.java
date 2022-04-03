package lesson1;

public class Main {
    public static void main(String[] args) {
        Member[] members = {
                new Human(100, 100, "Vasya"),
                new Cat(200, 200,"Barsik"),
                new Robot(300, 300,"Android")
        };
        Obstacle[] obstacles = {
                new Treadmill(150),
                new Wall(200)
        };

        for (Obstacle obstacle : obstacles) {
            for (Member member : members) {
                obstacle.pass(member);
            }
        }
    }
}
