package it.jgiem.myapps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import it.jgiem.myapps.calendar.MainActivityCalendar;
import it.jgiem.myapps.dateCounter.MainActivityDateCounter;
import it.jgiem.myapps.intent.MainActivityIntent;
import it.jgiem.myapps.musicPlayer.MainActivityMusic;
import it.jgiem.myapps.todoList.MainActivityTodoList;
import it.jgiem.myapps.voucher.MainActivityVoucher;
import it.jgiem.myapps.wordGuess.MainActivityWordGuess;
import it.jgiem.myapps.fontConverter.MainActivityFontConverter;
import it.jgiem.myapps.calculator.MainActivityCalculator;

public class MainActivity extends AppCompatActivity {

    public static List<App> app;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
//        imageViewRounded();


    }

//    private void imageViewRounded() {
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageView = findViewById(R.id.imageview);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guessword_icon);
//        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//
//        // Set the corner radius (half the height or width for a circle)
//        roundedBitmapDrawable.setCornerRadius(50f); // Set corner radius in pixels
//        imageView.setImageDrawable(roundedBitmapDrawable);
//    }

    private void initData() {
        app = List.of(
                new App(1, "Word Guess"),
                new App(2, "Font Converter"),
                new App(3, "Calculator"),
                new App(4, "Myanmar Calender"),
                new App(5, "Date Counter"),
                new App(6, "Intent Activities"),
                new App(7, "Music Player"),
                new App(8, "Voucher"),
                new App(9,"Todo List")
        );
    }

    public void onClicked(View view){
//        Intent intent = new Intent (this, MainActivityWordGuess.class);
//        startActivity(intent);


        MaterialCardView card = (MaterialCardView) view;
        LinearLayout layout = (LinearLayout) card.getChildAt(0);
        TextView text = (TextView) layout.getChildAt(1);
        String appName = text.getText().toString();
        appSwitch(appName);
    }

    private void appSwitch(String appName) {
        switch (appName){
            case "Word Guess":
                intent = new Intent (this, MainActivityWordGuess.class);
                break;
            case "Font Converter" :
                intent = new Intent(MainActivity.this, MainActivityFontConverter.class);
                break;
            case "Calculator" :
                intent = new Intent(MainActivity.this, MainActivityCalculator.class);
                break;
            case "Myanmar Calender" :
                intent = new Intent(MainActivity.this, MainActivityCalendar.class);
                break;
            case "Date Counter" :
                intent = new Intent(MainActivity.this, MainActivityDateCounter.class);
                break;
            case "Intent Activities" :
                intent = new Intent(MainActivity.this, MainActivityIntent.class);
                break;
            case "Music Player" :
                intent = new Intent(MainActivity.this, MainActivityMusic.class);
                break;
            case "Voucher" :
                intent = new Intent(MainActivity.this, MainActivityVoucher.class);
                break;
            case "Todo List" :
                intent = new Intent(MainActivity.this, MainActivityTodoList.class);
                break;

            default:
                Toast.makeText(this, ""+appName, Toast.LENGTH_SHORT).show();
        }
            switchIntent(intent);

    }

    private void switchIntent(Intent intent) {
        if(intent != null){
            startActivity(intent);
        }
    }
}