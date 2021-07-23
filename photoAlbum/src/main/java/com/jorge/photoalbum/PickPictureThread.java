package com.jorge.photoalbum;

/**
 * Created by Jorge on 7/23/21.
 */

public abstract class PickPictureThread extends Thread implements Runnable {

    public abstract void pickPictureThreadRun();
    @Override
    public void run() {
        pickPictureThreadRun();
    }
}
