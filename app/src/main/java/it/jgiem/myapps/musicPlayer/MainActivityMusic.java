package it.jgiem.myapps.musicPlayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import it.jgiem.myapps.R;

public class MainActivityMusic extends AppCompatActivity {

    public static List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        songs = List.of(
                new Song("BIRDS OF A FEATHER", R.drawable.billie, R.raw.billie1),
                new Song("CHIRORO", R.drawable.billie, R.raw.billie2),
                new Song("I Love You", R.drawable.billie, R.raw.billie3),
                new Song("Getting Older", R.drawable.billie, R.raw.billie4),
                new Song("Happier Than Ever", R.drawable.billie, R.raw.billie5),
                new Song("Lovely", R.drawable.billie, R.raw.billie6),
                new Song("Ocean Eyes", R.drawable.billie, R.raw.billie7),
                new Song("The 30th", R.drawable.billie, R.raw.billie8)
        );
    }

    public void onSongClicked(View view) {
        MaterialCardView card = (MaterialCardView) view;
        LinearLayout layout = (LinearLayout) card.getChildAt(0);
        TextView text = (TextView) layout.getChildAt(1);
        String songName = text.getText().toString();
        Song currentSong = songs.stream().filter(song-> song.name().equalsIgnoreCase(songName)).findFirst().get();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("song", currentSong);
//        intent.putExtra("title", currentSong.name());
//        intent.putExtra("image", currentSong.image());
        startActivity(intent);
    }
}