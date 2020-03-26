package liang.zhou.lane8.no5.opengl.shape;

import liang.zhou.lane8.no5.opengl.constant.NameOfVariantInShader;

public class Texture extends Shape {
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

    }

    @Override
    protected void initTextureCo() {
        super.initTextureCo();
    }
}
