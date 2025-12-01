package com.example.vehicleloancalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Initialize views
        TextView tvAuthorName = view.findViewById(R.id.tv_author_name);
        TextView tvMatricNo = view.findViewById(R.id.tv_matric_no);
        TextView tvCourse = view.findViewById(R.id.tv_course);
        TextView tvCR = view.findViewById(R.id.tv_cr);
        TextView tvGithubUrl = view.findViewById(R.id.tv_github_url);

        // Set author information - REPLACE WITH YOUR INFORMATION
        tvAuthorName.setText("MUHAMMAD FAHMI BIN RUSLI");
        tvMatricNo.setText("2023479866");
        tvCourse.setText("CS251");
        tvCR.setText("@copyright by Fahmi");
        tvGithubUrl.setText("https://github.com/yourusername/vehicle-loan-calculator");

        return view;
    }
}