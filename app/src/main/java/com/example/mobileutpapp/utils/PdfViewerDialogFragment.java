package com.example.mobileutpapp.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mobileutpapp.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewerDialogFragment extends DialogFragment {

    private static final String ARG_PDF_PATH = "pdf_path";
    private String pdfPath;

    public static PdfViewerDialogFragment newInstance(String pdfPath) {
        PdfViewerDialogFragment fragment = new PdfViewerDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PDF_PATH, pdfPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_pdf_viewer, container, false);
        PDFView pdfView = view.findViewById(R.id.pdfView);
        Button buttonClose = view.findViewById(R.id.button_close);

        if (getArguments() != null) {
            pdfPath = getArguments().getString(ARG_PDF_PATH);
        }

        if (pdfPath != null) {
            File file = new File(pdfPath);
            pdfView.fromFile(file).load();
        }

        buttonClose.setOnClickListener(v -> dismiss());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
