package com.example.jwanandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by SJC on 2020/6/6.
 * Describe：
 */
public class CommUtil {
    public final static int TYPE_CONTEXT = 0;

    public final static int TYPE_FOOT = 1;
    public final boolean LAST_ITEM = false;

    public final static int TYPE_KIND_1 = 0;
    public final static int TYPE_KIND_2 = 1;

    public final static boolean MODER_LOADING=false;


    public final static int TYPE_RECYCLER_TITLE=1;
    public final static int TYPE_RECYCLER_MESSAGE=2;



    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(Activity c) {
        try {
            InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(c.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {

        }
    }



}
