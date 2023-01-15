package com.example.appscoffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //MINUMAN
        final CheckBox coffe1 = findViewById(R.id.coffeamericano);
        final CheckBox coffe2 = findViewById(R.id.espresso);
        final CheckBox coffe3 = findViewById(R.id.capucino);
        final CheckBox coffe4 = findViewById(R.id.latte);
        final CheckBox coffe5 = findViewById(R.id.mocachino);
        final CheckBox coffe6 = findViewById(R.id.macchiato);

        //JUMLAH MINUMAN
        final EditText jml_cofe1 = findViewById(R.id.jumlahamericano);
        final EditText jml_cofe2 = findViewById(R.id.jumlahespresso);
        final EditText jml_cofe3 = findViewById(R.id.jumlahcapucino);
        final EditText jml_cofe4 = findViewById(R.id.jumlahlatte);
        final EditText jml_cofe5 = findViewById(R.id.jumlahmocachino);
        final EditText jml_cofe6 = findViewById(R.id.jumlahmacchiato);

        Button btn = findViewById(R.id.submit);

        //HARGA MINUMAN
        final int harga_americano = 15000;
        final int harga_espresso = 14000;
        final int harga_capucino = 16000;
        final int harga_latte = 19000;
        final int harga_mocachino = 18000;
        final int harga_macchiato = 17000;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!coffe1.isChecked() && !coffe2.isChecked() && !coffe3.isChecked() && !coffe4.isChecked() && !coffe5.isChecked() && !coffe6.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Silahkan Pilih Coffe Anda", Toast.LENGTH_SHORT).show();
                } else {
                    String text = "";
                    int totOrder = 0;
                    int discount = 0;
                    if (coffe1.isChecked()) {
                        String hasil = "Americano ";
                        int jml = Integer.parseInt(String.valueOf(jml_cofe1.getText()));
                        int cal = jml * harga_americano;
                        text += jml + "\t\t" + hasil + "\t\t\t\t\t\t\t\t\t" + "Rp. " + cal + "\n\n";
                        totOrder = totOrder + cal;
                    }
                    if (coffe2.isChecked()) {
                        String hasil = "Espresso ";
                        int jml = Integer.parseInt(String.valueOf(jml_cofe2.getText()));
                        int cal = jml * harga_espresso;
                        text += jml + "\t\t" + hasil + "\t\t\t\t\t\t\t\t\t" + "Rp. " + cal + "\n\n";
                        totOrder = totOrder + cal;
                    }
                    if (coffe3.isChecked()) {
                        String hasil = "Cappucino ";
                        int jml = Integer.parseInt(String.valueOf(jml_cofe3.getText()));
                        int cal = jml * harga_capucino;
                        text += jml + "\t\t" + hasil + "\t\t\t\t\t\t\t\t\t" + "Rp. " + cal + "\n\n";
                        totOrder = totOrder + cal;
                    }
                    if (coffe4.isChecked()) {
                        String hasil = "Latte Art ";
                        int jml = Integer.parseInt(String.valueOf(jml_cofe4.getText()));
                        int cal = jml * harga_latte;
                        text += jml + "\t\t" + hasil + "\t\t\t\t\t\t\t\t\t" + "Rp. " + cal + "\n\n";
                        totOrder = totOrder + cal;
                    }
                    if (coffe5.isChecked()) {
                        String hasil = "Mocachino ";
                        int jml = Integer.parseInt(String.valueOf(jml_cofe5.getText()));
                        int cal = jml * harga_mocachino;
                        text += jml + "\t\t" + hasil + "\t\t\t\t\t\t\t\t\t" + "Rp. " + cal + "\n\n";
                        totOrder = totOrder + cal;
                    }
                    if (coffe6.isChecked()) {
                        String hasil = "Macchiato ";
                        int jml = Integer.parseInt(String.valueOf(jml_cofe6.getText()));
                        int cal = jml * harga_macchiato;
                        text += jml + "\t\t" + hasil + "\t\t\t\t\t\t\t\t\t" + "Rp. " + cal + "\n\n";
                        totOrder = totOrder + cal;
                    }
                    Intent intent = new Intent(Menu.this, Pemesanan.class);
                    intent.putExtra("Coba", text);
                    intent.putExtra("Bayar", totOrder);
                    startActivity(intent);
                }
            }
        });
    }
}