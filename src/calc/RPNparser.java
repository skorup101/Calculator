package calc;

import java.io.File;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hubert Skorupka on 30.01.2018.
 */
public class RPNparser {
    private final Queue<String> rpn;

    /**Mapa zawierajaca dozwolone dzialania*/
    private static final Map<String, Integer> operatorsmap = new TreeMap<>();
    static{ operatorsmap.put("+", 2); operatorsmap.put("-", 2);
        operatorsmap.put("*", 3); operatorsmap.put("/",3);
        operatorsmap.put("^", 4);
    }

    public RPNparser(String input){
        rpn=toPolishNotation(input);
    }

    private static List<String> dividingString(String s){ //dzieli 22+3*2 na {22,+,3,*,2}
        Pattern p = Pattern.compile("\\d+[.]?\\d*|[+\\-*/^()]");
        Matcher m = p.matcher(s);
        List<String> array = new ArrayList<>();
        while(m.find()){
            array.add(m.group());
        }
        return array;
    }

    private static Queue<String> toPolishNotation(String infix){ // przerabia 2+2 na 2 2 +
        Queue<String> result = new LinkedBlockingQueue<>();
        Stack<String> operators = new Stack<>();
        String singedinfix = singed(infix); //tutaj przerabiamy wprowadzony ciag tak aby algorytm rozumial liczby ujemne

        for(String s : dividingString(singedinfix)) {
            if (s.matches("\\d+[.]?\\d*")) result.add(s);
            if (operatorsmap.containsKey(s)){
                if(operators.empty()) operators.push(s);
                else {
                    while (!operators.empty() && !operators.peek().equals("(")){
                        if((operatorsmap.get(operators.peek()) > operatorsmap.get(s)) || (operatorsmap.get(operators.peek()).equals(operatorsmap.get(s)) && !s.equals("^"))){
                            result.add(operators.pop());
                        } else{
                            break;
                        }
                    }
                    operators.push(s);
                }
            }
            if (s.equals("(")) operators.push(s);
            if (s.equals(")")){
                while(!operators.empty()){
                    if(operators.peek().equals("(")) {
                        operators.pop();
                        break;
                    }
                    else {
                        result.add(operators.pop());
                    }
                }
            }
        }
        while (!operators.empty()) result.add(operators.pop());
        return result;
    }

    private static String singed(String s){ // zamienia String -5 na 0-5 lub (-2) na (0-2)
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("^-|\\(-");
        Matcher m = p.matcher(s);
        while(m.find()){
            if (m.group().equals("-")) m.appendReplacement(sb, "0-");
            if (m.group().equals("(-"))m.appendReplacement(sb, "(0-");
        }
        m.appendTail(sb);
        return new String(sb);
    }

    @Override
    public String toString() {
        return "calc.RPNparser{" +
                "rpn= " + rpn +
                '}';
    }

    public Queue<String> getRpn() {
        return new LinkedBlockingQueue<>(rpn);
    }
}