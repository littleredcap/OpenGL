package liang.zhou.lane8.no5.opengl.glide;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;

@GlideModule
public class GlideModuleConfig extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new ProgressInterceptor());
        registry.replace(GlideUrl.class, InputStream.class,new OkHttpUrlLoader.Factory(builder.build()));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
