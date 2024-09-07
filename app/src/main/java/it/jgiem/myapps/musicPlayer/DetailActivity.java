package it.jgiem.myapps.musicPlayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.exoplayer.ExoPlayer;

import it.jgiem.myapps.R;
import it.jgiem.myapps.databinding.ActivityDetailMusicBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailMusicBinding binding;
    private Song song;
    private String title;
    private static ExoPlayer exoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMusicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListeners();
        initExoPlayer();
    }


    private void initExoPlayer() {
        if(exoPlayer == null)
            exoPlayer = new ExoPlayer.Builder(this).build();
        if(exoPlayer.isPlaying()) exoPlayer.stop();
        updatePlayer();

    }

    @androidx.annotation.OptIn(markerClass = UnstableApi.class)
    private void updatePlayer() {
        Uri songUri = RawResourceDataSource.buildRawResourceUri((song.song()));
        exoPlayer.setMediaItem(MediaItem.fromUri(songUri));
        exoPlayer.prepare();
        exoPlayer.play();
    }

    private void initListeners() {
        binding.btNext.setOnClickListener(v->{
            int index = MainActivityMusic.songs.indexOf(song);
            if (index == MainActivityMusic.songs.size() - 1){
                index = 0;
            } else{
                index++;
            }
            song = MainActivityMusic.songs.get(index);
            updateSongData(song);
            updatePlayer();
            binding.btPause.setImageResource(R.drawable.pause);

        });
        binding.btPrev.setOnClickListener(v->{
            int index = MainActivityMusic.songs.indexOf(song);
            if(index == 0){
                index = MainActivityMusic.songs.size()-1;
            } else {
                index--;
            }
            song = MainActivityMusic.songs.get(index);
            updateSongData(song);
            updatePlayer();
            binding.btPause.setImageResource(R.drawable.pause);

        });
        binding.btPause.setOnClickListener(v->{
            if(exoPlayer.isPlaying()){
                exoPlayer.pause();
                binding.btPause.setImageResource(R.drawable.play);
            } else {
                exoPlayer.play();
                binding.btPause.setImageResource(R.drawable.pause);
            }
        });
    }

    private void initUi() {
        Intent intent = getIntent();
        if (intent != null){
            song = (Song) intent.getSerializableExtra("song");
            updateSongData(song);
        }
    }



    private void updateSongData(Song song){
        binding.imageView.setImageDrawable(AppCompatResources.getDrawable(this, song.image()));
        binding.tvTitle.setText(song.name());

    }
}