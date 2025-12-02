package com.example.vehicleloancalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.vehicleloancalculator.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout using View Binding
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Set author information using the binding object
        binding.tvAuthorName.setText("MUHAMMAD FAHMI BIN RUSLI");
        binding.tvMatricNo.setText("2023479866");
        binding.tvCourse.setText("CS251");
        binding.tvCr.setText("@Copyright by Fahmi");

        String githubUrl = "https://github.com/Fahmirusli/CarLoan.git";
        binding.tvGithubUrl.setText(githubUrl);

        // Set OnClickListener to open the URL in a browser
        binding.tvGithubUrl.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
            startActivity(browserIntent);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Release the binding when the view is destroyed to avoid memory leaks
        binding = null;
    }
}
