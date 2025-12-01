package com.example.vehicleloancalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    private TextInputEditText etVehiclePrice, etDownPayment, etLoanPeriod, etInterestRate;
    private Button btnCalculate, btnClear;
    private TextView tvLoanAmount, tvTotalInterest, tvTotalPayment, tvMonthlyPayment;
    private CardView resultsCard;
    private DecimalFormat currencyFormat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views
        etVehiclePrice = view.findViewById(R.id.et_vehicle_price);
        etDownPayment = view.findViewById(R.id.et_down_payment);
        etLoanPeriod = view.findViewById(R.id.et_loan_period);
        etInterestRate = view.findViewById(R.id.et_interest_rate);

        btnCalculate = view.findViewById(R.id.btn_calculate);
        btnClear = view.findViewById(R.id.btn_clear);

        tvLoanAmount = view.findViewById(R.id.tv_loan_amount);
        tvTotalInterest = view.findViewById(R.id.tv_total_interest);
        tvTotalPayment = view.findViewById(R.id.tv_total_payment);
        tvMonthlyPayment = view.findViewById(R.id.tv_monthly_payment);

        resultsCard = view.findViewById(R.id.results_card);

        // Initialize currency format
        currencyFormat = new DecimalFormat("#,##0.00");

        // Set button listeners
        btnCalculate.setOnClickListener(v -> calculateLoan());
        btnClear.setOnClickListener(v -> clearFields());

        return view;
    }

    private void calculateLoan() {
        try {
            // Get input values
            String vehiclePriceStr = etVehiclePrice.getText().toString();
            String downPaymentStr = etDownPayment.getText().toString();
            String loanPeriodStr = etLoanPeriod.getText().toString();
            String interestRateStr = etInterestRate.getText().toString();

            // Validate inputs
            if (vehiclePriceStr.isEmpty() || downPaymentStr.isEmpty() ||
                    loanPeriodStr.isEmpty() || interestRateStr.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double vehiclePrice = Double.parseDouble(vehiclePriceStr);
            double downPayment = Double.parseDouble(downPaymentStr);
            int loanPeriod = Integer.parseInt(loanPeriodStr);
            double interestRate = Double.parseDouble(interestRateStr);

            // Validate values
            if (vehiclePrice <= 0) {
                Toast.makeText(getContext(), "Vehicle price must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }

            if (downPayment < 0) {
                Toast.makeText(getContext(), "Down payment cannot be negative", Toast.LENGTH_SHORT).show();
                return;
            }

            if (downPayment >= vehiclePrice) {
                Toast.makeText(getContext(), "Down payment must be less than vehicle price", Toast.LENGTH_SHORT).show();
                return;
            }

            if (loanPeriod <= 0) {
                Toast.makeText(getContext(), "Loan period must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }

            if (interestRate < 0) {
                Toast.makeText(getContext(), "Interest rate cannot be negative", Toast.LENGTH_SHORT).show();
                return;
            }

            // Perform calculations based on assignment requirements
            // Step 1: Loan Amount = Vehicle Price - Down Payment
            double loanAmount = vehiclePrice - downPayment;

            // Step 2: Total Interest = Loan Amount × (Interest Rate / 100) × Loan Period
            double totalInterest = loanAmount * (interestRate / 100) * loanPeriod;

            // Step 3: Total Payment = Loan Amount + Total Interest
            double totalPayment = loanAmount + totalInterest;

            // Step 4: Monthly Payment = Total Payment ÷ (Loan Period × 12)
            double monthlyPayment = totalPayment / (loanPeriod * 12);

            // Display results
            tvLoanAmount.setText("RM " + currencyFormat.format(loanAmount));
            tvTotalInterest.setText("RM " + currencyFormat.format(totalInterest));
            tvTotalPayment.setText("RM " + currencyFormat.format(totalPayment));
            tvMonthlyPayment.setText("RM " + currencyFormat.format(monthlyPayment));

            // Show results card
            resultsCard.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etVehiclePrice.setText("");
        etDownPayment.setText("");
        etLoanPeriod.setText("");
        etInterestRate.setText("");

        tvLoanAmount.setText("RM 0.00");
        tvTotalInterest.setText("RM 0.00");
        tvTotalPayment.setText("RM 0.00");
        tvMonthlyPayment.setText("RM 0.00");

        resultsCard.setVisibility(View.GONE);
    }
}