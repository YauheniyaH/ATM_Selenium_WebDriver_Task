package decorator_pattern;

public class HalloweenDecoration extends ClassDecorator{

    public HalloweenDecoration (ClassRoom classRoom){
        super(classRoom);
    }
    @Override
    public String decorate() {
        return super.decorate() + decorateForHalloween();
    }

    private String decorateForHalloween() {
        return " decorated for Halloween";
    }
}
