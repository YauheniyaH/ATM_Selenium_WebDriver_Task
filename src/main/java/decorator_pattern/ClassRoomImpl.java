package decorator_pattern;

public class ClassRoomImpl implements ClassRoom{
    public ClassRoomImpl() {
        super();
    }

    @Override
    public String decorate() {
        return "Class room";
    }
}
