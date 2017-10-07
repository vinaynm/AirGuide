package com.yogeshojha.airguide;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class SigninActivity extends AppCompatActivity {
    private Locale locale;
    private Button translate_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        translate_button = (Button) findViewById(R.id.btnchangelanguage);
        //listen to the button if clicked
        translate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the button is clicked then we show the spinner to the user and allow them
                //to choose the language
                showlanguagechangedialog();
            }
        });
    }
    public void showlanguagechangedialog() {
        //build the alert dialog from language_dialog.xml
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.language_dialog, null);
        dialogBuilder.setView(dialogView);
        final Spinner spinner1 = (Spinner) dialogView.findViewById(R.id.spinner1);
        dialogBuilder.setTitle(getResources().getString(R.string.translate_button_title));
        dialogBuilder.setMessage("Do you really wish to change the language?");
        dialogBuilder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int langpos = spinner1.getSelectedItemPosition();
                switch(langpos) {
                    case 0: //English
                        setLangRecreate("en");
                        return;
                    case 1: //Spanish
                        setLangRecreate("es");
                        return;
                    case 2: //Hindi
                        setLangRecreate("hi");
                        return;
                    case 3: //Japanese
                        setLangRecreate("ja");
                        return;
                    default: //By default we set the language to english
                        setLangRecreate("en");
                        return;
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //if clicked on cancel, do nothing
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    public void setLangRecreate(String langval) {
        //change the language
        Configuration config = getBaseContext().getResources().getConfiguration();
        locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}
