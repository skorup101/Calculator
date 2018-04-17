import calc.Equation;

public class MainApp {
    public static void main(String[] args) {
        Equation e = Equation.solve("5*3-22/3");
        System.out.println(e);
    }
}
