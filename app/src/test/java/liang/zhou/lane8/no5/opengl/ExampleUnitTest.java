package liang.zhou.lane8.no5.opengl;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println(Arrays.toString(search(new int[]{-3,-5,192,-3,2,1,8,5,2, -45, 23})));
    }

    /**
     * @param target 目标数组
     * @return
     */
    private int[] search(int[] target) {
        //sort(target, 0, target.length - 1);
        //sort(target);
        //bubble(target);
        //selection(target);
        //quick(target,0,target.length-1);
        //insert(target);
        //selectionSort(target);
        //quickSort(target,0,target.length-1);
        insert(target);
        return target;
    }

    public void selectionSort(int[] target){
        for(int i=0;i<target.length;i++){
            int minIndex=i;
            for(int j=i+1;j<target.length;j++){
                if(target[minIndex]>target[j]){
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                int temp=target[i];
                target[i]=target[minIndex];
                target[minIndex]=temp;
            }
        }
    }
    public void quickSort(int[] target,int low,int high){
        if(low>=high) return;
        int base=target[low];
        int left=low;
        int right=high;
        while(left<right){
            while(left<right&&target[right]>=base){
                right--;
            }
            target[left]=target[right];
            while(left<right&&target[left]<=base){
                left++;
            }
            target[right]=target[left];
        }
        target[left]=base;
        quick(target,low,left-1);
        quick(target,left+1,high);
    }
    public void insertSort(int[] target){
        for(int i=1;i<target.length;i++){
            int candidate=target[i];
            for(int j=i-1;j>=0;j--){
                if(candidate<target[j]){
                    target[j+1]=target[j];
                    target[j]=candidate;
                }
            }
        }
    }

    private void sort(int[] target,int low,int high){
        if(low>=high) return;
        int left=low;
        int right=high;
        int base=target[left];

        while(left<right){
            while(left<right&&target[right]>=base){
                right--;
            }
            target[left]=target[right];
            while(left<right&&target[left]<=base){
                left++;
            }
            target[right]=target[left];
        }
        target[left]=base;
        sort(target,low,left-1);
        sort(target,left+1,high);
    }

    private void sort(int[] target){
        for(int i=1;i<target.length;i++){
            int candidate=target[i];
            for(int j=i-1;j>=0;j--){
                if(candidate<=target[j]){
                    target[j+1]=target[j];
                    target[j]=candidate;
                }
            }
        }
    }

    private void bubble(int[] target){
        for(int i=0;i<target.length-1;i++){
            for(int j=0;j<target.length-1-i;j++){
                if(target[j]>target[j+1]){
                    int temp=target[j];
                    target[j]=target[j+1];
                    target[j+1]=temp;
                }
            }
        }
    }

    private void selection(int[] target){
        for(int i=0;i<target.length;i++){
            int minIndex=i;
            for(int j=i+1;j<target.length;j++){
                if(target[j]<target[minIndex]){
                    minIndex=j;
                }
            }
            int temp=target[i];
            target[i]=target[minIndex];
            target[minIndex]=temp;
        }
    }

    private void quick(int[] target,int left,int right){
        if(left>=right) return;
        int base=target[left];
        int low=left;
        int high=right;
        while(low<high){
            while(low<high&&target[high]>=base){
                high--;
            }
            target[low]=target[high];
            while(low<high&&target[low]<=base){
                low++;
            }
            target[high]=target[low];
        }
        target[low]=base;
        quick(target,left,low-1);
        quick(target,low+1,right);
    }

    private void insert(int[] target){
        for(int i=1;i<target.length;i++){
            int candidate=target[i];
            for(int j=i-1;j>=0;j--){
                if(candidate<target[j]){
                    target[j+1]=target[j];
                    target[j]=candidate;
                }
            }
        }
    }
}