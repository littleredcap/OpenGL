package liang.zhou.lane8.no5.opengl.shape;

import liang.zhou.lane8.no5.opengl.constant.NameOfVariantInShader;

public class Circle extends Shape {


    public Circle() {
        super();
    }

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
        //片段着色器
        fragmentShader =
            "precision mediump float;" +
                    "uniform vec4 " + NameOfVariantInShader.U_COLOR + ";" +
                    "void main() {" +
                    "  gl_FragColor = " + NameOfVariantInShader.U_COLOR + ";" +
                    "}";
    }

    @Override
    protected void initColor() {

    }

    @Override
    protected void initCoordinate() {
        float x = 0;
        float y = 0;
        float r = 0.6f;
        int index = -1;
        coordinate=new float[120*3*3];
        for (int i = 3; i <= 360; i = i + 3) {
            double d1 = i * Math.PI / 180;
            coordinate[++index] = 0;
            coordinate[++index] = 0;
            coordinate[++index] = 0;
            coordinate[++index] = (float) (x + r * Math.cos(d1 - 3 * Math.PI / 180));
            coordinate[++index] = (float) (y + r * Math.sin(d1 - 3 * Math.PI / 180));
            coordinate[++index] = 0;
            coordinate[++index] = (float) (x + r * Math.cos(d1));
            coordinate[++index] = (float) (y + r * Math.sin(d1));
            coordinate[++index] = 0;
        }
    }
}
