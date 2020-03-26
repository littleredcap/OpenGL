package liang.zhou.lane8.no5.opengl.shape;

public class Triangle extends Shape {

    @Override
    protected void initIndex() {

    }

    @Override
    protected void initShader() {

    }

    @Override
    protected void initColor() {

    }

    @Override
    protected void initCoordinate() {
        coordinate = new float[]{
                0f, 0.5f, 0.5f, // top
                -0.5f, -0.5f, 0.0f, // bottom left
                0.5f, -0.5f, 0.0f   // bottom right
        };
    }

}
