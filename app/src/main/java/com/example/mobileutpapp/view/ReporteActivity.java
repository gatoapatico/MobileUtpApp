package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PlatoController;
import com.example.mobileutpapp.entity.Plato;
import com.example.mobileutpapp.model.PlatoModel;
import com.example.mobileutpapp.utils.PdfViewerDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.util.List;

public class ReporteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private AppCompatImageButton btn_menu;
    private PlatoController platoController;

    Button reportePlatos;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportes);
        btn_menu = findViewById(R.id.bottomNavigationView);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        platoController = new PlatoController(this);
        List<Plato> platos = platoController.getAllPlatos();
        reportePlatos = findViewById(R.id.btn_reportePlatos);
        reportePlatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePdf(platos,"reporte_platos");
            }
        });
    }

    private void generatePdf(List<Plato> listaPlatos , String pdfName ) {
        String path = getFilesDir() + "/"+pdfName+".pdf";

        try {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título
            Paragraph title = new Paragraph(pdfName)
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20); // Espacio después del título

            document.add(title);

            // Crear tabla para mostrar las comidas
            Table table = new Table(4); // Cuatro columnas para id, nombre, precio y descripción

            // Agregar encabezados de columna
            table.addHeaderCell("ID");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Precio");
            table.addHeaderCell("Descripción");

            // Agregar filas de datos de comidas
            for (Plato plato : listaPlatos) {
                table.addCell(String.valueOf(plato.getId()));
                table.addCell(plato.getNombre());
                table.addCell(String.valueOf(plato.getPrecio()));
                table.addCell(plato.getDescripcion());
            }

            // Agregar tabla al documento
            document.add(table);
            document.close();

            // Mostrar el PDF usando DialogFragment
            PdfViewerDialogFragment pdfViewerDialogFragment = PdfViewerDialogFragment.newInstance(path);
            pdfViewerDialogFragment.show(getSupportFragmentManager(), "pdfViewer");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al generar PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_inicio:
                Intent intentInicio = new Intent(ReporteActivity.this, WelcomeActivity.class);
                startActivity(intentInicio);
                break;
            case R.id.nav_menu:
                Intent intentMenu = new Intent(ReporteActivity.this, MenuActivity.class);
                startActivity(intentMenu);
                break;
            case R.id.nav_ingInsumos:
                Intent intentAddSuply = new Intent(ReporteActivity.this, AddSupplyActivity.class);
                startActivity(intentAddSuply);
                break;
            case R.id.nav_almacen:
                Intent intentAlmacen = new Intent(ReporteActivity.this, AlmacenActivity.class);
                startActivity(intentAlmacen);
                break;
            case R.id.nav_reporte:
                Toast.makeText(this, "Ya estás en la pantalla de Reportes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(ReporteActivity.this, MainActivity.class);
                startActivity(logout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
