package com.Resepku.resepmakanan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Meal implements Parcelable {
    private String id;
    private String name;
    private String category;
    private String instructions;
    private String thumb;
    private String tags;
    private ArrayList<String> ingredients;
    private ArrayList<String> measures;

    public Meal(String id, String name, String category, String instructions, String thumb, String tags, ArrayList<String> ingredients, ArrayList<String> measures) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.instructions = instructions;
        this.thumb = thumb;
        this.tags = tags;
        this.ingredients = ingredients;
        this.measures = measures;
    }

    protected Meal(Parcel in) {
        id = in.readString();
        name = in.readString();
        category = in.readString();
        instructions = in.readString();
        thumb = in.readString();
        tags = in.readString();
        ingredients = in.createStringArrayList();
        measures = in.createStringArrayList();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getThumb() {
        return thumb;
    }

    public String[] getTags() {
        return this.tags.split(",");
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getMeasures() {
        return measures;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(category);
        parcel.writeString(instructions);
        parcel.writeString(thumb);
        parcel.writeString(tags);
        parcel.writeStringList(ingredients);
        parcel.writeStringList(measures);
    }
}
