package com.rugmail.kalinina.anya.loancalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //mLoanAmount - сумма кредита, mInterestRate - процентная ставка, mLoanPeriod - период кредитования
    //mMontlyPaymentResult - результат вычисления мес.платежа ,
    //mTotalPaymentsResult - результат вычисления общей суммы долга,
    //mOverpaymentsResult - результат вычисления переплаты
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mMontlyPaymentResult, mTotalPaymentsResult, mOverpaymentsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoanAmount = (EditText) findViewById(R.id.loan_amount);
        mInterestRate = (EditText) findViewById(R.id.interest_rate);
        mLoanPeriod = (EditText) findViewById(R.id.loan_period);
        mMontlyPaymentResult = (TextView) findViewById(R.id.monthly_payment_result);
        mOverpaymentsResult = (TextView) findViewById(R.id.overpayments_result);
        mTotalPaymentsResult = (TextView) findViewById(R.id.total_payments_result);
    }

    // метод, который обрабатывает нажание на кнопку "Рассчитать"
    public void calculate(View clickedButton) {

        //получение параметров из текстового поля и преобразование в число
        double loanAmount = Integer.parseInt(mLoanAmount.getText().toString());
        double interestRate = (Integer.parseInt(mInterestRate.getText().toString()));
        double loanPeriod = Integer.parseInt(mLoanPeriod.getText().toString());

        // (1) месячная процентная ставка
        double m = interestRate / 1200;

        // (2) месячная процентная ставка возведенная в степень равной количеству периодов (в месяцах)
        double m1 = Math.pow(m + 1, loanPeriod);

        //(3) расчет ежемесячного платежа по формуле
        double monthlyPayment = (double) ((m + (m / (m1 - 1))) * loanAmount);

        //(4) расчет Общей суммы долга
        double totalPayment = monthlyPayment * loanPeriod;

        // (5) расчет Переплаты
        double overpayments = totalPayment - loanAmount;

        //устанавливаем полученные результаты в виде текста
        mMontlyPaymentResult.setText(new DecimalFormat("##.##").format(monthlyPayment));
        mOverpaymentsResult.setText(new DecimalFormat("##.##").format(overpayments));
        mTotalPaymentsResult.setText(new DecimalFormat("##.##").format(totalPayment));
    }
}
