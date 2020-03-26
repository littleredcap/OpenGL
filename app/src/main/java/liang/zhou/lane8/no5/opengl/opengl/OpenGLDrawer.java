package liang.zhou.lane8.no5.opengl.opengl;

import android.graphics.Bitmap;
import android.opengl.GLES20;

import javax.microedition.khronos.opengles.GL10;

import liang.zhou.lane8.no5.opengl.constant.NameOfVariantInShader;
import liang.zhou.lane8.no5.opengl.shape.Circle;
import liang.zhou.lane8.no5.opengl.shape.Shape;
import liang.zhou.lane8.no5.opengl.shape.Sphere;
import liang.zhou.lane8.no5.opengl.shape.Square;
import liang.zhou.lane8.no5.opengl.shape.Triangle;

public class OpenGLDrawer {

    private int vPosition;
    private int uColor;
    private int vMatrix;
    private int textureCo;
    private int textureUnit;
    private float[] coordinate;
    private OpenGLConfig config;
    private OpenGLContentScale scale;

    public OpenGLDrawer(float[] texture,short[] index,float[] color,float[] coordinate,
                        String vertexShader,String fragmentShader){
        this.coordinate=coordinate;
        config=new OpenGLConfig(vertexShader,fragmentShader);
        config.performProgramPackage(vertexShader,fragmentShader);
        config.initVertexBuffer(coordinate);
        config.initColorBuffer(color);
        config.initIndexBuffer(index);
        config.initTextureBuffer(texture);
        scale=new OpenGLContentScale();
    }
    public OpenGLDrawer(Shape shape){
        this(shape.textureCo,shape.index,shape.color,shape.coordinate,shape.vertexShader,shape.fragmentShader);
    }

    public void drawBackground(float r,float g,float b,float a){
        config.configBackgroundColor(r,g,b,a);
    }
    public void viewPort(int leftX,int leftY,int width,int height){
        config.configViewPort(leftX,leftY,width,height);
    }

    public void scaleTexture(int width,int height,int textureWidth,int textureHeight) {
        //scale.scale(width,height);
        scale.changeMatrixInside(width,height,textureWidth,textureHeight);
    }
    public void scaleShape(int width,int height){
        scale.scale(width,height);
    }

    private void commonDraw(float color[],int shapeType,boolean withIndex,short index[]){
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT);
        initVertex(OpenGLConfig.stride,OpenGLConfig.coPerVertex);
        initColor(color);
        initMatrix();
        startDraw(shapeType,withIndex,index);
    }
    private void startDraw(int shapeType,boolean withIndex,short index[]){

        if(withIndex&&index!=null) {
            //索引法绘制正方体
            GLES20.glDrawElements(shapeType,
                    index.length, GLES20.GL_UNSIGNED_SHORT, config.mIndexFloatBuffer);
        }else{
            //绘制三角形.
            //draw arrays的几种方式 GL_TRIANGLES三角形
            //GL_TRIANGLE_STRIP三角形带的方式(开始的3个点描述一个三角形，后面每多一个点，多一个三角形)
            //GL_TRIANGLE_FAN扇形(可以描述圆形)
            GLES20.glDrawArrays(shapeType, 0, coordinate.length / 3);
        }
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(vPosition);
    }
    public void drawTriangle(Shape s){
        Shape temp;
        if (s instanceof Triangle){
            temp=s;
        }else{
            temp=new Triangle();
        }
        commonDraw(temp.color,GLES20.GL_TRIANGLES,false,null);
    }
    public void drawSquare(Shape s){
        Shape temp;
        if (s instanceof Square){
            temp=s;
        }else{
            temp=new Square();
        }
        commonDraw(temp.color,GLES20.GL_TRIANGLE_FAN,false,null);
    }
    public void drawCircle(Shape s){
        Shape temp;
        if (s instanceof Circle){
            temp=s;
        }else{
            temp=new Circle();
        }
        commonDraw(temp.color,GLES20.GL_TRIANGLE_FAN,false,null);
    }
    public void drawSphere(Shape s){
        Shape temp;
        if (s instanceof Sphere){
            temp=s;
        }else{
            temp=new Sphere();
        }
        commonDraw(temp.color,GLES20.GL_TRIANGLES,false,null);
    }

    private void initMatrix(){
        if(scale.projectionMatrix!=null) {
            vMatrix = GLES20.glGetUniformLocation(OpenGLConfig.program, NameOfVariantInShader.U_MATRIX);
            GLES20.glUniformMatrix4fv(vMatrix, 1,
                    false, scale.projectionMatrix, 0);
        }
    }
    private void initVertex(int stride,int size) {
        //1.根据我们定义的取出定义的位置
        vPosition = GLES20.glGetAttribLocation(OpenGLConfig.program, NameOfVariantInShader.V_POSITION);
        //2.开始启用我们的position
        GLES20.glEnableVertexAttribArray(vPosition);
        //3.将坐标数据放入
        GLES20.glVertexAttribPointer(
                vPosition,  //上面得到的id
                size, //告诉他用几个偏移量来描述一个顶点
                GLES20.GL_FLOAT, false,
                stride, //一个顶点需要多少个字节的偏移量
                config.mVertexFloatBuffer);
    }
    private void initColor(float color[]){
        //取出颜色
        uColor = GLES20.glGetUniformLocation(OpenGLConfig.program, NameOfVariantInShader.U_COLOR);
        //开始绘制
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(uColor,1,color,0);
        /*
        //获取片元着色器的vColor成员的句柄
        uColor = GLES20.glGetAttribLocation(OpenGLConfig.program, NameOfVariantInShader.U_COLOR);
        //设置绘制三角形的颜色
        GLES20.glEnableVertexAttribArray(uColor);
        GLES20.glVertexAttribPointer(uColor,OpenGLConfig.coPerVertex,
                GLES20.GL_FLOAT,false,
                OpenGLConfig.stride,config.mColorFloatBuffer);*/
    }
    public void drawCube(Shape s){
        /*GLES10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        initMatrix();
        initVertex();
        initColor(s.color);
        startDraw(GLES20.GL_TRIANGLES,true,s.index);*/
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT| GLES20.GL_DEPTH_BUFFER_BIT);
        //将程序加入到OpenGLES2.0环境
        //获取变换矩阵vMatrix成员句柄
        //指定vMatrix的值
        GLES20.glUniformMatrix4fv(vMatrix,1,false,scale.projectionMatrix,0);
        //获取顶点着色器的vPosition成员句柄
        vPosition = GLES20.glGetAttribLocation(OpenGLConfig.program, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(vPosition);
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(vPosition, 3,
                GLES20.GL_FLOAT, false,
                0, config.mVertexFloatBuffer);
        //获取片元着色器的vColor成员的句柄
        uColor = GLES20.glGetAttribLocation(OpenGLConfig.program, "aColor");
        //设置绘制三角形的颜色
        // GLES20.glUniform4fv(mColorHandle, 2, color, 0);
        GLES20.glEnableVertexAttribArray(uColor);
        GLES20.glVertexAttribPointer(uColor,4,
                GLES20.GL_FLOAT,false,
                0,config.mColorFloatBuffer);
        //索引法绘制正方体
        GLES20.glDrawElements(GLES20.GL_TRIANGLES,s.index.length, GLES20.GL_UNSIGNED_SHORT,config.mIndexFloatBuffer);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(vPosition);
    }

    public void drawTexture(Bitmap b,Shape shape){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // 传入顶点坐标
        initVertex(0,shape.coPerVertex);
        initMatrix();
        initTexture(b);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, shape.coordinate.length /shape.coPerVertex);
        GLES20.glDisableVertexAttribArray(vPosition);
        GLES20.glDisableVertexAttribArray(textureCo);
    }

    private void initTexture(Bitmap b){
        config.configTexturePackage(b);
        initTextureUnit();
        initTextureCo();
    }
    private void initTextureCo(){
        textureCo = GLES20.glGetAttribLocation(OpenGLConfig.program, "aTexCoord");
        // 传入纹理坐标
        GLES20.glEnableVertexAttribArray(textureCo);
        GLES20.glVertexAttribPointer(textureCo, 2, GLES20.GL_FLOAT, false,
                0, config.mTextureFloatBuffer);
    }
    private void initTextureUnit(){
        textureUnit = GLES20.glGetAttribLocation(OpenGLConfig.program, "uTextureUnit");
        // 把选定的纹理单元传给片段着色器
        GLES20.glUniform1i(textureUnit, 0);
    }
}
