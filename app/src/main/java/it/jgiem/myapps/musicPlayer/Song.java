package it.jgiem.myapps.musicPlayer;

import android.media.Image;

import androidx.annotation.DrawableRes;

import java.io.Serializable;

public record Song(String name, @DrawableRes int image, int song) implements Serializable {
}
