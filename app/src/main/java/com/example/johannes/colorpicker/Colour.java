package com.example.johannes.colorpicker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by johannes on 14.12.2015.
 */
public class Colour implements Parcelable {

    private String hex;
    private int[] rgb;

    public Colour() {
    }

    public Colour(String hex, int[] rgb) {
        this.hex = hex;
        this.rgb = rgb;
    }

    protected Colour(Parcel in) {
        hex = in.readString();
        rgb = in.createIntArray();
    }

    public static final Creator<Colour> CREATOR = new Creator<Colour>() {
        @Override
        public Colour createFromParcel(Parcel in) {
            return new Colour(in);
        }

        @Override
        public Colour[] newArray(int size) {
            return new Colour[size];
        }
    };

    public String getHex() {
        return hex;
    }

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    @Override
    public String toString() {
        return "Colour{" +
                "hex='" + hex + '\'' +
                ", rgb=" + Arrays.toString(rgb) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hex);
        dest.writeIntArray(rgb);
    }
}
