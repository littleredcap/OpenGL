package liang.zhou.lane8.no5.opengl.shape;

import liang.zhou.lane8.no5.opengl.constant.NameOfVariantInShader;

public class Sphere extends Shape {
    @Override
    protected void initIndex() {
        index=new short[]{
                0, 1, 2,
                0, 2, 3
        };
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

    }

    @Override
    protected void initCoordinate() {
        float x = 0;
        float y = 0;
        float z = -1;
        float r = 1;
        int index=0;
        coordinate=new float[20 * 40 * 6 * 3];
        double d = 9 * Math.PI / 180;
        for (int i = 0; i < 180; i += 9) {
            double d1 = i * Math.PI / 180;
            for (int j = 0; j < 360; j += 9) {
                double d2 = j * Math.PI / 180;
                coordinate[index++] = (float) (x + r * Math.sin(d1 + d) * Math.cos(d2 + d));
                coordinate[index++] = (float) (y + r * Math.cos(d1 + d));
                coordinate[index++] = (float) (z + r * Math.sin(d1 + d) * Math.sin(d2 + d));

                coordinate[index++] = (float) (x + r * Math.sin(d1) * Math.cos(d2));
                coordinate[index++] = (float) (y + r * Math.cos(d1));
                coordinate[index++] = (float) (z + r * Math.sin(d1) * Math.sin(d2));

                coordinate[index++] = (float) (x + r * Math.sin(d1) * Math.cos(d2 + d));
                coordinate[index++] = (float) (y + r * Math.cos(d1));
                coordinate[index++] = (float) (z + r * Math.sin(d1) * Math.sin(d2 + d));

                coordinate[index++] = (float) (x + r * Math.sin(d1 + d) * Math.cos(d2 + d));
                coordinate[index++] = (float) (y + r * Math.cos(d1 + d));
                coordinate[index++] = (float) (z + r * Math.sin(d1 + d) * Math.sin(d2 + d));

                coordinate[index++] = (float) (x + r * Math.sin(d1 + d) * Math.cos(d2));
                coordinate[index++] = (float) (y + r * Math.cos(d1 + d));
                coordinate[index++] = (float) (z + r * Math.sin(d1 + d) * Math.sin(d2));

                coordinate[index++] = (float) (x + r * Math.sin(d1) * Math.cos(d2));
                coordinate[index++] = (float) (y + r * Math.cos(d1));
                coordinate[index++] = (float) (z + r * Math.sin(d1) * Math.sin(d2));

            }
        }
    }
}
