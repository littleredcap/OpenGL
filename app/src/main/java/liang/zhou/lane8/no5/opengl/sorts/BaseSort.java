package liang.zhou.lane8.no5.opengl.sorts;

public class BaseSort implements ISort {

    protected String name="base";

    @Override
    public void startSort(int[] target) {
        System.out.println("开始"+name+"算法");
    }
}
