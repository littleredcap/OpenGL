package liang.zhou.lane8.no5.opengl.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import liang.zhou.lane8.no5.opengl.R;

public class BitmapUtil {

    public static Bitmap getBitmap(Context context){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inScaled=false;
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.yukee,options);
    }
}
