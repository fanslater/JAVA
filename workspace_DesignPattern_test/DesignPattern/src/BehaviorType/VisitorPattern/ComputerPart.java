package BehaviorType.VisitorPattern;

public interface ComputerPart
{
    public void accept(ComputerPartVisitor computerPartVisitor);
}
