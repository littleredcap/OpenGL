package liang.zhou.lane8.no5.opengl.shape;

public class TextureCircle extends Texture {

    public TextureCircle(){
        coPerVertex=3;
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
            coordinate[++index] = 0;//以上3个位圆心坐标
            coordinate[++index] = (float) (x + r * Math.cos(d1 - 3 * Math.PI / 180));
            coordinate[++index] = (float) (y + r * Math.sin(d1 - 3 * Math.PI / 180));
            coordinate[++index] = 0;//0即为z轴坐标值
            coordinate[++index] = (float) (x + r * Math.cos(d1));
            coordinate[++index] = (float) (y + r * Math.sin(d1));
            coordinate[++index] = 0;
            //每个三角形都有三个顶点，因此需要9个坐标值
        }

    }

    @Override
    protected void initTextureCo() {
        super.initTextureCo();
        float x = 0.5f;
        float y = 0.5f;
        float r = 0.5f;
        int index = -1;
        textureCo=new float[120*3*3];
        for (int i = 3; i <= 360; i = i + 3) {
            double d1 = i * Math.PI / 180;
            textureCo[++index] = x;
            textureCo[++index] = y;
            textureCo[++index] = (float) (y + r * Math.sin(d1 - 3 * Math.PI / 180));
            textureCo[++index] = (float) (x + r * Math.cos(d1 - 3 * Math.PI / 180));
            textureCo[++index] = (float) (y + r * Math.sin(d1));
            textureCo[++index] = (float) (x + r * Math.cos(d1));

        }
    }
}
