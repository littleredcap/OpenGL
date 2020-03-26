package liang.zhou.lane8.no5.opengl.trash_bin;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import liang.zhou.lane8.no5.opengl.shape.Triangle;

public class MyGlSurfaceView extends GLSurfaceView
        implements GLSurfaceView.Renderer {

    private Triangle shape;
    private static final int COORDS_PER_VERTEX = 3;
    private static final int COORDS_PER_COLOR = 0;

    //在数组中，描述一个顶点，总共的顶点需要的偏移量。这里因为只有位置顶点，所以和上面的值一样
    private static final int TOTAL_COMPONENT_COUNT = COORDS_PER_VERTEX+COORDS_PER_COLOR;
    //一个点需要的byte偏移量。
    private static final int STRIDE = TOTAL_COMPONENT_COUNT * 4;
    private static float TRIANGLE_COORDS[] = {
            //Order of coordinates: X, Y, Z
            0.5f, 0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f   // bottom right
    };

    public MyGlSurfaceView(Context context) {
        this(context,null);
    }

    public MyGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(RENDERMODE_WHEN_DIRTY);

        shape=new Triangle();
    }
    private int mProgramObjectId;

    private FloatBuffer mVertexFloatBuffer;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1,1,1,1);
        mVertexFloatBuffer = ByteBuffer
                .allocateDirect(TRIANGLE_COORDS.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(TRIANGLE_COORDS);
        //因为是从第一个点开始，就表示三角形的，所以将position移动到0
        mVertexFloatBuffer.position(0);
        String vertexShaderCode = "attribute vec4 aPosition;"+
                "void main() {" +
                "gl_Position = aPosition;" +
                "}";
        String fragmentShaderCode =
                "precision mediump float;" +
                "uniform vec4 uColor;" +
                "void main(){"+
                "gl_FragColor = uColor;" +
                "}";
        //1.得到之后，进行编译。得到id

        int vertexShaderObjectId = compileShaderCode(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShaderObjectId = compileShaderCode(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        //3.继续套路。取得到program
        mProgramObjectId = GLES20.glCreateProgram();
        //将shaderId绑定到program当中
        GLES20.glAttachShader(mProgramObjectId, vertexShaderObjectId);
        GLES20.glAttachShader(mProgramObjectId, fragmentShaderObjectId);
        //4.最后，启动GL link program
        GLES20.glLinkProgram(mProgramObjectId);
    }

    public static int compileShaderCode(int type, String shaderCode) {
        //得到一个着色器的ID。主要是对ID进行操作
        int shaderObjectId = GLES20.glCreateShader(type);

        //如果着色器的id不为0，则表示是可以用的
        if (shaderObjectId != 0) {
            //0.上传代码
            GLES20.glShaderSource(shaderObjectId, shaderCode);
            //1.编译代码.根据刚刚和代码绑定的ShaderObjectId进行编译
            GLES20.glCompileShader(shaderObjectId);

            //2.查询编译的状态
            int[] status = new int[1];
            //调用getShaderIv ，传入GL_COMPILE_STATUS进行查询
            GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, status, 0);

            if (status[0] == 0) { //等于0。则表示失败
                //失败的话，需要释放资源，就是删除这个引用
                GLES20.glDeleteShader(shaderObjectId);
                return 0;
            }
        }
        //最后都会去返回这个shader的引用id
        return shaderObjectId;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width,height);
    }

    private float TRIANGLE_COLOR[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d("drawerDraw","program:"+mProgramObjectId+",coordinate:"+TRIANGLE_COORDS.length);
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT);
        GLES20.glUseProgram(mProgramObjectId);

        //1.根据我们定义的取出定义的位置
        int vPosition = GLES20.glGetAttribLocation(mProgramObjectId, "aPosition");
        //2.开始启用我们的position
        GLES20.glEnableVertexAttribArray(vPosition);
        //3.将坐标数据放入
        GLES20.glVertexAttribPointer(
                vPosition,  //上面得到的id
                COORDS_PER_VERTEX, //告诉他用几个偏移量来描述一个顶点
                GLES20.GL_FLOAT, false,
                STRIDE, //一个顶点需要多少个字节的偏移量
                mVertexFloatBuffer);

        //取出颜色
        int uColor = GLES20.glGetUniformLocation(mProgramObjectId, "uColor");

        //开始绘制
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(
                uColor,
                1,
                TRIANGLE_COLOR,
                0
        );

        //绘制三角形.
        //draw arrays的几种方式 GL_TRIANGLES三角形
        //GL_TRIANGLE_STRIP三角形带的方式(开始的3个点描述一个三角形，后面每多一个点，多一个三角形)
        //GL_TRIANGLE_FAN扇形(可以描述圆形)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, TRIANGLE_COORDS.length / 3);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(vPosition);
    }
}
