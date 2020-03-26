package liang.zhou.lane8.no5.opengl.shape;

import liang.zhou.lane8.no5.opengl.constant.NameOfVariantInShader;

public class TextureRec extends Shape {

    public TextureRec(){
        coPerVertex=2;
    }
    @Override
    protected void initIndex() {

    }

    @Override
    protected void initShader() {
        vertexShader =
                "uniform mat4 " + NameOfVariantInShader.U_MATRIX + ";" +
                        "attribute vec4 " + NameOfVariantInShader.V_POSITION + ";" +
                        "attribute vec2 aTexCoord;" +
                        "varying vec2 vTexCoord;" +
                        "void main() {" +
                        "  gl_Position = " + NameOfVariantInShader.U_MATRIX + " * " +
                        NameOfVariantInShader.V_POSITION + ";" +
                        "  vTexCoord = aTexCoord;" +
                        "}";
        fragmentShader =
                "precision mediump float;" +
                        "uniform sampler2D uTextureUnit;" +
                        "varying vec2 vTexCoord;" +
                        "void main() {" +
                        "  gl_FragColor = texture2D(uTextureUnit, vTexCoord);" +
                        "}";
    }

    @Override
    protected void initColor() {

    }

    @Override
    protected void initCoordinate() {
        coordinate = new float[]{
                1, 1,  // top right
                -1, 1, // top left
                1, -1, // bottom right
                -1, -1,
        };
    }

    @Override
    protected void initTextureCo() {
        textureCo = new float[]{
                1, 0,  // top right
                0, 0,  // top left
                1, 1,  // bottom right
                0, 1,
                /*-0.5f,0.5f,0f,
                -0.5f,-0.5f,0f,
                0.5f,-0.5f,0f,
                0.5f, 0.5f,0f,*/
        };
    }
}
