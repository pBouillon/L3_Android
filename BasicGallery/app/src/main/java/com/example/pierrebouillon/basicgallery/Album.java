package com.example.pierrebouillon.basicgallery;

import android.support.annotation.NonNull;

import java.util.Iterator;

public class Album implements Iterable{
    /**  */
    private static Album INSTANCE = new Album() ;

    /**
     *
     */
    private Album() {}

    /**
     * @return
     */
    public static Album getInstance() {
        return INSTANCE ;
    }

    /**
     *
     */
    public void add (Picture p ) {

    }

    /**
     *
     */
    public Picture get (int i) {
        return null ;
    }

    /**
     *
     */
    public long getId (int i) {
        return 0 ;
    }

    /**
     *
     */
    public void delete (int i) {

    }

    @NonNull
    @Override
    public Iterator<Picture> iterator() {
        return null ;
    }
}
