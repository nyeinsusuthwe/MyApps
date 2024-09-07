package it.jgiem.myapps;

import androidx.annotation.DrawableRes;

import java.io.Serializable;

public record App(int appID, String name) implements Serializable {
}
