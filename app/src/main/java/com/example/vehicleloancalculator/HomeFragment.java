package com.example.vehicleloancalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.vehicleloancalculator.databinding.FragmentHomeBinding;
import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DecimalFormat currencyFormat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout using View Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Initialize currency format
        currencyFormat = new DecimalFormat("#,##0.00");

        // Set button listeners using the binding object
        binding.btnCalculate.setOnClickListener(v -> calculateLoan());
        binding.btnClear.setOnClickListener(v -> clearFields());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Release the binding when the view is destroyed to avoid memory leaks
        binding = null;
    }

    private void calculateLoan() {
        // Use the binding object to access views
        if (binding == null) return;

        try {
            // Get input values
            String vehiclePriceStr = binding.etVehiclePrice.getText().toString();
            String downPaymentStr = binding.etDownPayment.getText().toString();
            String loanPeriodStr = binding.etLoanPeriod.getText().toString();
            String interestRateStr = binding.etInterestRate.getText().toString();

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

            // Perform calculations
            double loanAmount = vehiclePrice - downPayment;
            double totalInterest = loanAmount * (interestRate / 100) * loanPeriod;
            double totalPayment = loanAmount + totalInterest;
            double monthlyPayment = totalPayment / (loanPeriod * 12);

            // Display results
            binding.tvLoanAmount.setText("RM " + currencyFormat.format(loanAmount));
            binding.tvTotalInterest.setText("RM " + currencyFormat.format(totalInterest));
            binding.tvTotalPayment.setText("RM " + currencyFormat.format(totalPayment));
            binding.tvMonthlyPayment.setText("RM " + currencyFormat.format(monthlyPayment));

            // Show results card
            binding.resultsCard.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        if (binding == null) return;
        
        binding.etVehiclePrice.setText("");
        binding.etDownPayment.setText("");
        binding.etLoanPeriod.setText("");
        binding.etInterestRate.setText("");

        binding.tvLoanAmount.setText("RM 0.00");
        binding.tvTotalInterest.setText("RM 0.00");
        binding.tvTotalPayment.setText("RM 0.00");
        binding.tvMonthlyPayment.setText("RM 0.00");

        binding.resultsCard.setVisibility(View.GONE);
    }
}
