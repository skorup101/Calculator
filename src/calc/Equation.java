package calc;

public class Equation {
    private double result;
    private String equation;
    private Equation(String equation){
        this.equation = equation;
        RPNparser parser = new RPNparser(equation);
        RPNsolver solver = new RPNsolver(parser.getRpn());
        result = solver.getOutput();
    }
    public static Equation solve(String equation){
        return new Equation(equation);
    }
    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "calc.Equation {" + equation + "}\n" +
                "RESULT= {" + result + "}\n";
    }
}
