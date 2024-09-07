package it.jgiem.myapps.wordGuess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import it.jgiem.myapps.R;

public class MainActivityWordGuess extends AppCompatActivity {


    private String[] guessWords = {"banana", "mango", "orange", "apple"};
    private TextView tv;
    private String randomWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wordguess);

        tv = findViewById(R.id.tv_word_guess);
        setRandomWordToTextView();

        EditText et = findViewById(R.id.et_your_guess);


        Button bt = findViewById(R.id.bt_guess);
        bt.setOnClickListener(v->{
            String myGuess = et.getText().toString();
            if(myGuess.equals(randomWord)){
                Toast.makeText(this, "You guess right!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
            }
            setRandomWordToTextView();
            et.getText().clear();
        });
    }

    private void setRandomWordToTextView(){
        randomWord = getRandomWord();
        tv.setText(shuffleWord(randomWord));
    }


    private String shuffleWord(String randomWord){
        //String to char array
        char[] words = randomWord.toCharArray();

        //char array to list
        List<Character> wordList = new ArrayList<>();
        for (char word: words){
            wordList.add(word);
        }
        Collections.shuffle(wordList);

        //list to String
        String value = "";
        for (char word : wordList){
            value += word;
        }
        return value;
    }

    private String getRandomWord(){
        Random random = new Random();
        int index = random.nextInt(guessWords.length);
        return guessWords[index];
    }
}