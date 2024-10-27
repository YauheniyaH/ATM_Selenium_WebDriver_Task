package decorator_pattern;

public abstract class ClassDecorator implements ClassRoom{
    private ClassRoom classRoom;


    public ClassDecorator(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String decorate() {
        return classRoom.decorate();
    }
}
