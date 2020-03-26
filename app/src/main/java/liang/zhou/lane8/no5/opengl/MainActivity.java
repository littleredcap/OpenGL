package liang.zhou.lane8.no5.opengl;

import androidx.appcompat.app.AppCompatActivity;
import liang.zhou.lane8.no5.opengl.glide.GlideApp;
import liang.zhou.lane8.no5.opengl.glide.ProgressInterceptor;
import liang.zhou.lane8.no5.opengl.glide.ProgressListener;
import liang.zhou.lane8.no5.opengl.ui.TestGLSurfaceView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {

    TestGLSurfaceView glSurfaceView;
    private ImageView iv;
    private String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585041072013&di=f3c31038a9e604ea9f10199e557bb0df&imgtype=0&src=http%3A%2F%2Fbbs.jooyoo.net%2Fattachment%2FMon_0905%2F24_65548_2835f8eaa933ff6.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //glSurfaceView=findViewById(R.id.heart);

        iv=findViewById(R.id.iv);
        ProgressInterceptor.addListener(url, new ProgressListener() {
            @Override
            public void onProgressUpdate(int progress) {
                Log.d("onRead",progress+"");
            }
        });
        GlideApp.with(this).load(url).skipMemoryCache(true).
                diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }


}
