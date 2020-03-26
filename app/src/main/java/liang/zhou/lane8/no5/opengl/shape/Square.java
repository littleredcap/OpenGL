package liang.zhou.lane8.no5.opengl.shape;

import liang.zhou.lane8.no5.opengl.constant.NameOfVariantInShader;

public class Square extends Shape {
    @Override
    protected void initIndex() {

    }

    @Override
    protected void initShader() {
        vertexShader =
                "attribute vec4 " + NameOfVariantInShader.V_POSITION + ";" +
                        "uniform mat4 "+NameOfVariantInShader.U_MATRIX+";" +
                        "void main() {" +
                        "  gl_Position = " + NameOfVariantInShader.V_POSITION +
                        "*" + NameOfVariantInShader.U_MATRIX+";" +
                        "}";
    }

    @Override
    protected void initColor() {
        float []  cubeColors = {
                1f,0f,0f,1f ,
                0f,1f,0f,1f,
                0f,0f,1f,1f,
                1f,0f,0f,1f,

                1f,0f,0f,1f ,
                0f,1f,0f,1f,
                0f,0f,1f,1f,
                1f,0f,0f,1f,

                1f,0f,0f,1f ,
                0f,1f,0f,1f,
                0f,0f,1f,1f,
                1f,0f,0f,1f,

                1f,0f,0f,1f ,
                0f,1f,0f,1f,
                0f,0f,1f,1f,
                1f,0f,0f,1f,


                1f,0f,0f,1f ,
                0f,1f,0f,1f,
                0f,0f,1f,1f,
                1f,0f,0f,1f,

                1f,0f,0f,1f ,
                0f,1f,0f,1f,
                0f,0f,1f,1f,
                1f,0f,0f,1f,
        };
    }

    @Override
    protected void initCoordinate() {
        coordinate = new float[]{
                -0.5f,0.5f,0f,
                -0.5f,-0.5f,0f,
                0.5f,-0.5f,0f,
                0.5f, 0.5f,0f,
        };
    }
}
