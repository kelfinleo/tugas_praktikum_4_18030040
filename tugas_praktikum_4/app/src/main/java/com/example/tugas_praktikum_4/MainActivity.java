package com.example.tugas_praktikum_4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener{
    private EditText phoneNumber;
    private EditText websiteUri;
    private EditText locationUri;
    private EditText textShare;  Button buttonWebsite;
    Button buttonLocation;
    Button buttonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = findViewById(R.id.edtPhoneNumber);
        websiteUri = findViewById(R.id.edtWebsiteUri);
        locationUri = findViewById(R.id.edtLocationUri);
        textShare = findViewById(R.id.edtShareText);
        buttonWebsite = findViewById(R.id.btnWebsiteUri);
        buttonWebsite.setOnClickListener(this);
        buttonWebsite = findViewById(R.id.btnLocationUri);
        buttonWebsite.setOnClickListener(this);
        buttonWebsite = findViewById(R.id.btnShareText);
        buttonWebsite.setOnClickListener(this);
    }
    public void openDialPhone(View view) {
        if (phoneNumber.getText().toString()==null || phoneNumber.getText().toString().trim().equals("")){
            Toast.makeText(getBaseContext(),"Harus Di isi", Toast.LENGTH_LONG).show();
        }
        else {
            Intent dialPhone = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + phoneNumber.getText().toString()));
            startActivity(dialPhone);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWebsiteUri:
                if (websiteUri.getText().toString()==null || websiteUri.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(),"Harus Di isi", Toast.LENGTH_LONG).show();
                }
                else {
                    String url = websiteUri.getText().toString();
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://" + url;
                        Intent openWebsite = new
                                Intent(Intent.ACTION_VIEW, Uri.parse
                                (url));
                        startActivity(openWebsite);
                    }
                }
                break;
            case R.id.btnLocationUri:
                Intent openLocation = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" +
                        locationUri.getText().toString()));
                startActivity(openLocation);
                break;
            case R.id.btnShareText:
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType("text/plan")
                        .setChooserTitle("Share this text with : "
                        )
                        .setText(textShare.getText().toString())  .startChooser();
                break;
        }
    }
}