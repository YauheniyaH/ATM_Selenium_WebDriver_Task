package decorator_pattern;

public class ClassRoomDecorationDemo {
    public static void main(String[] args) {
        ClassRoom room1 = new ChristmasDecoration(new ClassRoomImpl());
        System.out.println(room1.decorate());

        ClassRoom room2 = new HalloweenDecoration(new ChristmasDecoration(new ClassRoomImpl()));
        System.out.println(room2.decorate());

    }
}
