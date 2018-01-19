package com.example.pierrebouillon.basicgallery;

import android.net.Uri;

import java.util.GregorianCalendar;

public class Picture {
    /**  */
    private int cpt ;
    /**  */
    private long id ;
    /**  */
    private String name ;

    /**
     * @param name
     * @param uri
     */
    public Picture(String name, Uri uri) {
        this.name = name ;
    }

    /**
     *
     */
    public String getDate() {
        return null ;
    }

    /**
     *
     */
    public void setDate(GregorianCalendar date) {

    }

    /**
     *
     */
    public String getName() {
        return this.name ;
    }

    /**
     *
     */
    public void setName (String newName) {
        name = newName ;
    }

    /**
     *
     */
    public long getId () {
        return id ;
    }

    /**
     *
     */
    public void setId (long newId) {
        id = newId ;
    }

    /**
     *
     */
    public Uri getUri() {
        return null ;
    }

    /**
     *
     */
    public void setUri (Uri newUri) {

    }

    /**
     *
     */
    public String toString() {
        return null ;
    }
}
