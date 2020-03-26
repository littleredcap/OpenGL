package liang.zhou.lane8.no5.opengl.trash_bin;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLESUtils {
    public static final int SIZEOF_FLOAT = 4;
    public static int createImageTexture(Bitmap bmp) {
        if (bmp == null) {
            return 0;
        }
        int[] textureHandles = new int[1];
        GLES20.glGenTextures(1, textureHandles, 0);
        if (textureHandles[0] == 0) {
            return 0;
        }

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
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        // 生成 MIP 贴图
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        return textureHandles[0];
    }
    public static FloatBuffer createFloatBuffer(float[] coords) {
        // Allocate a direct ByteBuffer, using 4 bytes per float, and copy coords into it.
        ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * SIZEOF_FLOAT);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(coords);
        fb.position(0);
        return fb;
    }
    public static int createVertexShader(String shaderCode) {
        return createShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }
    public static int createShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        // add the source code to the shader and compile it
        GLES20.glCompileShader(shader);
        int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            //Log.e(TAG, "compile shader: " + type + ", error: " + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            shader = 0;
        }
        return shader;
    }
    public static int createFragmentShader(String shaderCode) {
        return createShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }
    public static int createImageTexture(ByteBuffer data, int width, int height, int format) {
        int[] textureHandles = new int[1];
        int textureHandle;

        GLES20.glGenTextures(1, textureHandles, 0);
        textureHandle = textureHandles[0];

        // Bind the texture handle to the 2D texture target.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle);

        // Configure min/mag filtering, i.e. what scaling method do we use if what we're rendering
        // is smaller or larger than the source image.
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);

        // Load the data from the buffer into the texture handle.
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, format,
                width, height, 0, format, GLES20.GL_UNSIGNED_BYTE, data);

        return textureHandle;
    }
    public static int createProgram(int vertexShader, int fragmentShader) {
        if (vertexShader == 0 || fragmentShader == 0) {
        }
        int program = GLES20.glCreateProgram();
        if (program == 0) {
            return 0;
        }
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] != GLES20.GL_TRUE) {
            //Log.e(TAG, "link program error: " + GLES20.glGetProgramInfoLog(program));
            GLES20.glDeleteProgram(program);
            program = 0;
        }
        return program;
    }
    public static float[] changeMvpMatrixInside(float viewWidth, float viewHeight,
                                                float textureWidth, float textureHeight) {
        float scale = viewWidth * textureHeight / viewHeight / textureWidth;
        float[] mvp = new float[16];
        Matrix.setIdentityM(mvp, 0);
        Matrix.scaleM(mvp, 0, scale > 1 ? (1F / scale) : 1F, scale > 1 ? 1F : scale, 1F);
        return mvp;
    }
}
