package com.example.escaperoom.hangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.escaperoom.R;

public class GameScreen extends Fragment {

    ImageView image;
    TextView text,chars,round;
    Button submitbutton;
    EditText wordsubmit;

    int stage = 1;

    private AlertDialog.Builder dialogBuilder;
    private Dialog dialog;
    private TextView poptitle;
    private ImageView popimage;
    private TextView popdesc;
    private Button popbutton;

    Hangman thisgame;

    public GameScreen() {
        super(R.layout.activity_game_screen);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View binding = inflater.inflate(R.layout.fragment_game_screen, container, false);

        thisgame = hangman_create(this, binding);





        image = (ImageView)binding.findViewById(R.id.hangmanimage);
        text = (TextView)binding.findViewById(R.id.worddisplay);
        submitbutton = (Button)binding.findViewById(R.id.submitbutton);
        chars = (TextView)binding.findViewById(R.id.chars);
        round = (TextView)binding.findViewById(R.id.round);


        submitbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wordsubmit = (EditText)binding.findViewById(R.id.wordsubmit);
                if (!wordsubmit.getText().equals("") && wordsubmit.getText().length() > 0) {
                    thisgame.guess();
                }
                wordsubmit.setText("");
            }
        });


        setstage();

        thisgame.prep();

        return binding;
    }

    public Hangman hangman_create(GameScreen gameScreen, View binding) {
        Hangman hangman = new Hangman(this,binding);
        return hangman;
    }

    public void setstage() {
        String output = String.format("Stage %d/3", stage);
        round.setText(output);
    }

    public void popup_lost(View binding) {
        dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(binding.getContext() , R.style.popuptheme));
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_lost, null);

        poptitle = (TextView) contactPopupView.findViewById(R.id.popup_lost_title);
        popbutton = (Button) contactPopupView.findViewById(R.id.popup_lost_button);
        popimage = (ImageView) contactPopupView.findViewById(R.id.popup_lost_image);
        popdesc = (TextView) contactPopupView.findViewById(R.id.popup_lost_desc);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(null);

        popbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                stage = 1;
                thisgame.prep();
            }
        });
    }

    public void popup_win(View binding) {
        dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(binding.getContext(), R.style.popuptheme));
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_win, null);

        poptitle = (TextView) contactPopupView.findViewById(R.id.popup_win_title);
        popbutton = (Button) contactPopupView.findViewById(R.id.popup_win_button);
        popimage = (ImageView) contactPopupView.findViewById(R.id.popup_win_image);
        popdesc = (TextView) contactPopupView.findViewById(R.id.popup_win_desc);

        if (stage > 2) {
            popbutton.setText("Return to room");
        }
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(null);

        popbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                stage++;
                if (stage > 3) {
                    //win code here
                    Navigation.findNavController(binding).navigate(R.id.action_gameScreen2_to_startFragment);
                }
                else {
                    setstage();
                    thisgame.prep();
                };
            }
        });
    }

}