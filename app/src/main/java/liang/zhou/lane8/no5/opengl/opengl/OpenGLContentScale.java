package liang.zhou.lane8.no5.opengl.opengl;

import android.opengl.Matrix;

public class OpenGLContentScale {

    float[] projectionMatrix ;

    public OpenGLContentScale(){
    }

    public void scale(int originalWidth,int originalHeight){
        if(projectionMatrix==null) {
            projectionMatrix = new float[16];
        }
        //处理变形
        final float aspectRatio = originalWidth > originalHeight ?
                originalWidth * 1f / originalHeight : originalHeight * 1f / originalWidth;
        if (originalWidth > originalHeight) {
            Matrix.orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio,
                    -1f, 1f, -1f, 1f);
        } else {
            Matrix.orthoM(projectionMatrix, 0, -1f, 1f,
                    -aspectRatio, aspectRatio, -1f, 1f);
        }
    }
    public void changeMatrixInside(float viewWidth, float viewHeight,
                                                float textureWidth, float textureHeight) {
        float scale = viewWidth * textureHeight / viewHeight / textureWidth;
        if(projectionMatrix==null) {
            projectionMatrix = new float[16];
        }
        Matrix.setIdentityM(projectionMatrix, 0);
        Matrix.scaleM(projectionMatrix, 0, scale > 1 ? (1F / scale) : 1F, scale > 1 ? 1F : scale, 1F);
    }
}
