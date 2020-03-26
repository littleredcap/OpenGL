package liang.zhou.lane8.no5.opengl.opengl;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class OpenGLConfig{

    static int program;
    static int coPerVertex= 3;
    //一个点需要的byte偏移量。
    static int stride = coPerVertex * 4;
    static int vertexShaderObjectId;
    static int fragmentShaderObjectId;
    static int textureId;
    FloatBuffer mVertexFloatBuffer;
    FloatBuffer mColorFloatBuffer;
    ShortBuffer mIndexFloatBuffer;
    FloatBuffer mTextureFloatBuffer;

    public OpenGLConfig(String vertexShader,String fragmentShader){
        performProgramPackage(vertexShader,fragmentShader);
        configBackgroundColor(0,1,1,1);
    }

    /**
     * 从创建到绑定
     * @param vertexShader
     * @param fragmentShader
     */
    public void performProgramPackage(String vertexShader,String fragmentShader){
        program= GLES20.glCreateProgram();//这个方法一定要在surfaceCreated中调用，否则为0
        performShaderPackage(vertexShader,fragmentShader);
        GLES20.glLinkProgram(program);
        GLES20.glUseProgram(program);
    }

    public void configTexturePackage(Bitmap bitmap){
        if (bitmap == null) {
            OpenGLConfig.textureId=0;
        }
        int[] textureHandles = new int[1];
        GLES20.glGenTextures(1, textureHandles, 0);
        if (textureHandles[0] == 0) {
            OpenGLConfig.textureId=0;
        }
        // 激活纹理
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // Bind the texture handle to the 2D texture target.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandles[0]);
        // Configure min/mag filtering, i.e. what scaling method do we use if what we're rendering
        // is smaller or larger than the source image.
        // 设置缩小过滤为三线性过滤
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR);
        // 设置放大过滤为双线性过滤
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        // 加载纹理到 OpenGL，读入 Bitmap 定义的位图数据，并把它复制到当前绑定的纹理对象
        // Load the data from the buffer into the texture handle.
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        // 生成 MIP 贴图
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
        OpenGLConfig.textureId=textureHandles[0];
    }
    /**
     * 从创建到绑定
     * @param vertexShader
     * @param fragmentShader
     */
    public void performShaderPackage(String vertexShader,String fragmentShader){
        vertexShaderObjectId = createShader(GLES20.GL_VERTEX_SHADER,vertexShader);
        fragmentShaderObjectId = createShader(GLES20.GL_FRAGMENT_SHADER,fragmentShader);
        GLES20.glAttachShader(program,vertexShaderObjectId);
        GLES20.glAttachShader(program,fragmentShaderObjectId);
    }
    private Buffer initBuffer(float targets[], int perElementSize) {
        if(targets==null) return null;
        Buffer buffer=ByteBuffer
                .allocateDirect(targets.length * perElementSize)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(targets);
        buffer.position(0);
        return buffer;
    }
    public void initColorBuffer(float colors[]){
        mColorFloatBuffer= (FloatBuffer) initBuffer(colors,4);
    }
    public void initVertexBuffer(float vertex[]){
        mVertexFloatBuffer= (FloatBuffer) initBuffer(vertex,4);
    }
    public void initTextureBuffer(float texture[]){
        mTextureFloatBuffer= (FloatBuffer) initBuffer(texture,4);
    }
    public void initIndexBuffer(short index[]){
        if(index==null) return;
        Buffer buffer=ByteBuffer
                .allocateDirect(index.length * 2)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(index);
        buffer.position(0);
        mIndexFloatBuffer= (ShortBuffer) buffer;
    }
    public void configBackgroundColor(float r,float g,float b,float a){
        GLES20.glClearColor(r,g,b,a);
    }
    public void configViewPort(int leftX,int leftY,int width,int height){
        GLES20.glViewport(leftX,leftY,width,height);
    }
    private int createShader(int type,String source){
        int shaderObjectId = GLES20.glCreateShader(type);
        //如果着色器的id不为0，则表示是可以用的
        if (shaderObjectId != 0) {
            //0.上传代码
            GLES20.glShaderSource(shaderObjectId, source);
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
}
