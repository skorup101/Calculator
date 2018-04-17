import calc.Equation;

public class CalcMainCommandStyle {
    public static void main(String[] args) {
        if(args.length<1) {
            System.out.println("No args.\n Usage: String equation eg: 5+(3-2)*7^(2)\n" +
                    "in order to use singed (-) numbers use as following: 0-5+(3-0-2)*7");
            System.exit(1);
        }else try {
            System.out.println(Equation.solve(args[0]));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
