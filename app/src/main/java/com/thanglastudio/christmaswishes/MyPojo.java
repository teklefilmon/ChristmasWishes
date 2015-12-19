package com.thanglastudio.christmaswishes;

/**
 * Created by Filmon on 12/10/2015.
 */
public class MyPojo {
    String wishes;
    int image;

    public MyPojo(String wishes, int image) {
        this.wishes = wishes;
        this.image = image;
    }

    public String getWishes() {
        return wishes;
    }

    public int getImage() {
        return image;
    }
}
