package decorator_pattern;

public class ChristmasDecoration extends ClassDecorator{
    private static final String MESSAGE = " decorated for Christmas";
    public ChristmasDecoration (ClassRoom classRoom){
        super(classRoom);
    }
    @Override
    public String decorate() {
        return super.decorate() + MESSAGE;
    }

}
