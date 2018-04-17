package calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class RPNsolver {
    private double output;
    public RPNsolver(Queue<String> inputQueue) {
        output = solve(inputQueue);
    }

    public Double getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return String.valueOf(output);
    }

    private static double solve(Queue q){
        Queue<String> inque = q;
        Queue<String> tempque = new LinkedBlockingQueue<>();
        Queue<String> outque = new LinkedBlockingQueue<>();
        if (inque.size()<3){
            System.out.format("Wprowadzona kolejka jest za krótka: inqueue.size()= %d < 3.\n", inque.size());
            return 0;
        }
        //System.out.println(inque);
        while(inque.size()>1) {
            while (!inque.isEmpty()) {
                if (tempque.size() < 2) {
                    tempque.add(inque.poll());
                }
                if (tempque.size() == 2 && inque.peek().matches("[-]?\\d+[.]?\\d*")) {
                    outque.add(tempque.poll());
                    tempque.add(inque.poll());
                }
                if (tempque.size() == 2 && !inque.peek().matches("[-]?\\d+[.]?\\d*")) {
                    tempque.add(inque.poll());
                    outque.add(compute(tempque).toString());
                    tempque.clear();
                    break;
                }
            }
            while (!inque.isEmpty()) {
                outque.add(inque.poll());
            }
            //System.out.println(outque);
            inque = new LinkedBlockingQueue<>(outque);
            outque.clear();
        }
        return Double.parseDouble(inque.poll());
    }

    private static Double compute(Queue<String> q3){
        int i=0;
        List<Double> doublelist = new ArrayList<>();
        while(i<2){
            i++;
            doublelist.add(Double.parseDouble(q3.poll()));
        }
        switch(q3.peek()){
            case "+": return doublelist.get(0)+doublelist.get(1);
            case "-": return doublelist.get(0)-doublelist.get(1);
            case "*": return doublelist.get(0)*doublelist.get(1);
            case "/": return doublelist.get(0)/doublelist.get(1);
            case "^": return Math.pow(doublelist.get(0), doublelist.get(1).intValue());
            default: return 0d;
        }
    }
}

