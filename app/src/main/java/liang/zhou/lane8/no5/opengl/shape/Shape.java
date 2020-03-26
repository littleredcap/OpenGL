package liang.zhou.lane8.no5.opengl.shape;

import liang.zhou.lane8.no5.opengl.constant.NameOfVariantInShader;
import liang.zhou.lane8.no5.opengl.opengl.OpenGLDrawer;

public abstract class Shape {

    public String name = "shape";
    public int coPerVertex=3;


    public String vertexShader =
            "attribute vec4 "+ NameOfVariantInShader.V_POSITION +";" +
                    "void main() {" +
                    "gl_Position = "+ NameOfVariantInShader.V_POSITION +";" +
                    "}";
    public String fragmentShader =
            "precision mediump float;" +
                    "uniform vec4 "+NameOfVariantInShader.U_COLOR+";" +
                    "void main(){" +
                    "gl_FragColor = "+NameOfVariantInShader.U_COLOR+";" +
                    "}";
    ;
    public OpenGLDrawer drawer;
    public float coordinate[];
    public short index[];
    public float textureCo[];
    public float color[] = {1f, 0f, 0f, 1.0f};

    public Shape() {
        initShader();
        initColor();
        initCoordinate();
        initIndex();
        initTextureCo();
    }

    /**
     * 初始化纹理坐标
     */
    protected void initTextureCo(){}

    protected abstract void initIndex();

    protected abstract void initShader();

    protected abstract void initColor();

    protected abstract void initCoordinate();

    private void drawOnBackground(float r, float g, float b, float a) {
        drawer.drawBackground(r, g, b, a);
    }

    private void draw() {
        //drawer.draw(color);
    }

    private void viewPort(int leftX, int leftY, int width, int height) {
        drawer.viewPort(leftX, leftY, width, height);
    }
    private void scale(int width,int height){
        //drawer.scaleContent(width,height);
    }
}
