package com.Resepku.resepmakanan.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Category implements Parcelable {
    private String id;
    private String name;
    private String thumb;
    private String desc;

    public Category(String id, String name, String thumb, String desc) {
        this.id = id;
        this.name = name;
        this.thumb = thumb;
        this.desc = desc;
    }

    protected Category(Parcel in) {
        id = in.readString();
        name = in.readString();
        thumb = in.readString();
        desc = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumb() {
        return thumb;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(thumb);
        parcel.writeString(desc);
    }
}
