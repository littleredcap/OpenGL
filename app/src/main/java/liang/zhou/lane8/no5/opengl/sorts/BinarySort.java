package liang.zhou.lane8.no5.opengl.sorts;

public class BinarySort extends BaseSort {

    public BinarySort(){
        name="快速排序";
    }

    @Override
    public void startSort(int[] target) {
        super.startSort(target);
        sort(target);
    }

    private void sort(int[] target){
        int base=target[0];

    }
}
