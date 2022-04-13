package ru.mirea.baskakov.mireaproject.ui.calculate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import java.math.BigDecimal;
import java.math.RoundingMode;

import ru.mirea.baskakov.mireaproject.R;

public class CalculateFragment extends Fragment {

    private TextView resultTextView;
    private String fistNum = "";
    private String secondNum = "";
    private Operation operation;
    private boolean operationFinished = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        resultTextView = (TextView) view.findViewById(R.id.textView);
        view.findViewById(R.id.one_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.two_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.three_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.four_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.five_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.six_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.seven_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.eight_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.nine_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.null_btm).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.point_btn).setOnClickListener(this::onNumberButtonClick);

        view.findViewById(R.id.equals_btn).setOnClickListener(this::equalsBtnOnClick);
        view.findViewById(R.id.del_btn2).setOnClickListener(this::delBtnOnClick);
        view.findViewById(R.id.div_btn).setOnClickListener(this::divBtnOnClick);
        view.findViewById(R.id.mltp_btn).setOnClickListener(this::mltpBtnOnClick);
        view.findViewById(R.id.min_btn).setOnClickListener(this::minBtnOnClick);
        view.findViewById(R.id.sum_btn).setOnClickListener(this::sumBtnOnClick);
        view.findViewById(R.id.ac_btn).setOnClickListener(this::acBtnOnClick);

        return view;
    }

    private void equalsBtnOnClick(View view){
        performOperation();
        fistNum = "";
        secondNum = resultTextView.getText().toString();
    }

    private void delBtnOnClick(View view){
        if (secondNum.length() > 0){
            secondNum = secondNum.substring(0, secondNum.length()-1);
            resultTextView.setText(secondNum);
        }
    }

    private void sumBtnOnClick(View view){
        performAction(Operation.PLUS);
    }

    private void minBtnOnClick(View view){
        performAction(Operation.MINUS);
    }

    private void divBtnOnClick(View view){
        performAction(Operation.DIVIDE);
    }

    private void mltpBtnOnClick(View view){
        performAction(Operation.MULTI);
    }

    private void acBtnOnClick(View view){
        fistNum = "";
        secondNum = "";
        resultTextView.setText(secondNum);
        operation = null;
    }

    private void performOperation(){

        if (secondNum.equals(""))
            secondNum = "0";

        double resultNumber = 0.0;

        boolean divOnNull = false;

        if (!fistNum.equals("")) {
            if (operation == Operation.PLUS) {
                resultNumber = Double.parseDouble(fistNum)
                        + Double.parseDouble(secondNum);

            } else if (operation == Operation.DIVIDE) {
                if (Double.parseDouble(secondNum) == 0){
                    divOnNull = true;
                }
                else{
                    resultNumber = Double.parseDouble(fistNum)
                            / Double.parseDouble(secondNum);
                }

            } else if (operation == Operation.MULTI) {
                resultNumber = Double.parseDouble(fistNum)
                        * Double.parseDouble(secondNum);

            } else if (operation == Operation.MINUS) {
                resultNumber = Double.parseDouble(fistNum)
                        - Double.parseDouble(secondNum);
            }

            if (divOnNull){
                resultTextView.setText("Error");
                secondNum = "";
                operationFinished = true;
            }
            else{
                resultNumber = new BigDecimal(resultNumber)
                        .setScale(2, RoundingMode.HALF_EVEN). doubleValue();
                fistNum = String.valueOf(resultNumber);
                resultTextView.setText(fistNum);
                secondNum = "";
                operationFinished = true;
            }
        }
    }

    private void onNumberButtonClick(View view){
        if(operationFinished){
            secondNum = "";
            operationFinished = false;
        }
        Button button = (Button) view;
        if(secondNum.equals("") && button.getText().toString().equals(".")){
            secondNum += "0";
        }
        secondNum += button.getText().toString();
        resultTextView.setText(secondNum);
    }

    private void performAction(Operation operation){
        if(fistNum.equals("")){
            this.operation = operation;
            fistNum = secondNum;
            secondNum = "";
            resultTextView.setText("");
        } else {
            performOperation();
            this.operation = operation;
        }
    }
}