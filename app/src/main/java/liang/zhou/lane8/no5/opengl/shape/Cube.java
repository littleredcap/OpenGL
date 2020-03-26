package liang.zhou.lane8.no5.opengl.shape;

public class Cube extends Shape {
    @Override
    protected void initIndex() {
        index = new short[]{
                6, 7, 4, 6, 4, 5,    //后面
                6, 3, 7, 6, 2, 3,    //右面
                6, 5, 1, 6, 1, 2,    //下面
                0, 3, 2, 0, 2, 1,    //正面
                0, 1, 5, 0, 5, 4,    //左面
                0, 7, 3, 0, 4, 7,
        };
    }

    @Override
    protected void initShader() {
        /*vertexShader =
                "attribute vec4 " + NameOfVariantInShader.V_POSITION + ";" +
                        "uniform mat4 " + NameOfVariantInShader.U_MATRIX + ";" +
                        "varying  vec4 " + NameOfVariantInShader.U_COLOR + ";" +
                        "attribute vec4 " + NameOfVariantInShader.A_COLOR + ";" +
                        "void main() {" +
                        "  gl_Position = " + NameOfVariantInShader.U_MATRIX +
                        "*" + NameOfVariantInShader.V_POSITION + ";" +
                        "  vColor=" + NameOfVariantInShader.A_COLOR + ";" +
                        "}";*/
        vertexShader =
                "attribute vec4 vPosition;" +
                        "uniform mat4 vMatrix;" +
                        "varying  vec4 vColor;" +
                        "attribute vec4 aColor;" +
                        "void main() {" +
                        "  gl_Position = vMatrix*vPosition;" +
                        "  vColor=aColor;" +
                        "}";
        ;
        fragmentShader =
                "precision mediump float;" +
                        "varying vec4 vColor;" +
                        "void main() {" +
                        "  gl_FragColor = vColor;" +
                        "}";
    }

    @Override
    protected void initColor() {
        color = new float[]{
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,
                0f, 1f, 0f, 1f,
                1f, 0f, 0f, 1f,
                1f, 0f, 0f, 1f,
                1f, 0f, 0f, 1f,
                1f, 0f, 0f, 1f,
        };
    }

    @Override
    protected void initCoordinate() {
        coordinate = new float[]{
                -1.0f, 1.0f, 1.0f,    //正面左上0
                -1.0f, -1.0f, 1.0f,   //正面左下1
                1.0f, -1.0f, 1.0f,    //正面右下2
                1.0f, 1.0f, 1.0f,     //正面右上3
                -1.0f, 1.0f, -1.0f,    //反面左上4
                -1.0f, -1.0f, -1.0f,   //反面左下5
                1.0f, -1.0f, -1.0f,    //反面右下6
                1.0f, 1.0f, -1.0f,     //反面右上7
        };
    }
}
