package com.example.booknews.utils;

import android.content.Context;
import android.widget.Toast;

public class FunctionUtils {
    public static final String sliderModelError = "Heroes is empty. slideModels is fail";
    public static final String getDataIsEmpty = "Heroes is empty";
    public static final String inValidInput = "Name is invalid or blank";
    public static final String getHeroFromBundleFail = "get hero from bundle failed" ;

    public static void showToast(String mes, Context context){
        Toast.makeText(context, mes, Toast.LENGTH_SHORT).show();
    }
}
