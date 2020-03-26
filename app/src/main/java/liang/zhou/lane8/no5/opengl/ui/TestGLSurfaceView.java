package liang.zhou.lane8.no5.opengl.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import liang.zhou.lane8.no5.opengl.opengl.OpenGLDrawer;
import liang.zhou.lane8.no5.opengl.shape.Heart;
import liang.zhou.lane8.no5.opengl.shape.Shape;
import liang.zhou.lane8.no5.opengl.utils.BitmapUtil;

public class TestGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer {

    private OpenGLDrawer drawer;
    private Shape shape;
    private Bitmap b;

    public TestGLSurfaceView(Context context) {
        this(context, null);
    }

    public TestGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8,
                8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setRenderer(this);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
        setZOrderOnTop(true);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        shape = new Heart();
        drawer = new OpenGLDrawer(shape.textureCo, shape.index, shape.color,
                shape.coordinate, shape.vertexShader, shape.fragmentShader);
        drawer.drawBackground(0, 0, 0, 0f);
        b = BitmapUtil.getBitmap(getContext());
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        drawer.viewPort(0, 0, width, height);
        //drawer.scaleTexture(width,height,b.getWidth(),b.getHeight());
        //drawer.scaleShape(width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //drawer.drawCircle(shape);
        drawer.drawCircle(shape);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode=MeasureSpec.getMode(widthMeasureSpec);
        int wSize=MeasureSpec.getSize(widthMeasureSpec);
        int hMode=MeasureSpec.getMode(heightMeasureSpec);
        int hSize=MeasureSpec.getSize(heightMeasureSpec);
    }
}
