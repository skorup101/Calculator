package UI;
import calc.Equation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CalcUI extends JFrame{
    private static final int WIDTH = 500;
    private static final int HEIGHT= 100;
    private final FrameLocalization location;
    private final EvaluateButton evaluateButton;
    private InputTxtField inputTxtField;
    private JLabel resultLabel;

    public CalcUI(){
        super("Calculator");
        setSize(WIDTH,HEIGHT);
        location = new FrameLocalization(WIDTH,HEIGHT);
        setLocation(location.x,location.y);
        setLayout(new GridLayout(1,3));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        evaluateButton = new EvaluateButton("=");

        inputTxtField = new InputTxtField("Input your equation here...");

        resultLabel = new JLabel("0.00");
        resultLabel.setHorizontalAlignment(JLabel.RIGHT);

        add(inputTxtField);
        add(evaluateButton);
        add(resultLabel);

        setVisible(true);
    }

    class InputTxtField extends JTextField implements FocusListener{
        private InputTxtField(String s){
            super(s);
            addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
            select(0,0);
        }
    }

    class EvaluateButton extends JButton implements ActionListener{
        private EvaluateButton(String s ){
            super(s);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Equation equation = Equation.solve(inputTxtField.getText());
            String result = Double.toString(equation.getResult());
            resultLabel.setText(result);
        }
    }
}

