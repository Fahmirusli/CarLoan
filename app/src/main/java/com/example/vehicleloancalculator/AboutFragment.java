package com.example.vehicleloancalculator;

import android.content.Intent;
import android.net.Uri;
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

        // Set author information
        tvAuthorName.setText("MUHAMMAD FAHMI BIN RUSLI");
        tvMatricNo.setText("2023479866");
        tvCourse.setText("CS251");
        tvCR.setText("@Copyright by Fahmi");

        String githubUrl = "https://github.com/Fahmirusli/CarLoan.git";
        tvGithubUrl.setText(githubUrl);

        // Set OnClickListener to open the URL in a browser
        tvGithubUrl.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
            startActivity(browserIntent);
        });

        return view;
    }
}
