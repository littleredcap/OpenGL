package liang.zhou.lane8.no5.opengl.shape;

public class Heart extends Shape {
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
        float x = 0;
        float y = 0;
        float r ;
        int index = -1;
        coordinate = new float[120 * 3 * 3];
        for (int i = 3; i <= 360; i = i + 3) {
            double d1 = i * Math.PI / 180;
            r = (float) (0.3 * (Math.sin(d1) * Math.sqrt(Math.abs(Math.cos(d1))) /
                    (Math.sin(d1) + 7f / 5f) - 2f * Math.sin(d1) + 2f));
            coordinate[++index] = 0;
            coordinate[++index] = 0;
            coordinate[++index] = 0;
            coordinate[++index] = (float) (x + r * Math.cos(d1 - 3 * Math.PI / 180));
            coordinate[++index] = (float) (y + r * Math.sin(d1 - 3 * Math.PI / 180));
            coordinate[++index] = 0;
            coordinate[++index] = (float) ((r * Math.cos(d1)) );
            coordinate[++index] = (float) ((r * Math.sin(d1)) );
            coordinate[++index] = 0;
        }
    }
}
