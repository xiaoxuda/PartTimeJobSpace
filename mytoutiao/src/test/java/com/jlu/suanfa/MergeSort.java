package com.jlu.suanfa;

/**
 * Created by Administrator on 2016/8/4.
 */
public class MergeSort {
    public static void main(String[] args){
        int[] array = {3,2,1,5,4,6,7};
        mergeSort(array,0,6);
        show(array);
    }

    private static void show(int[] array){
        int i=0;
        while(i < array.length){
            System.out.print(array[i]+" ");
            i++;
        }
    }

    private static void mergeSort(int[] array,int start,int end){
        int mid = (start+end)/2;
        if(start != end){
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
        }

        merge(array,start,mid,end);
    }
    private static void merge(int[] array,int start,int mid,int end){
        int shift = end-start;
        //把当前要合并的数据复制到一个temp数组中
        int[] temp = new int[shift+1];
        for(int i = start;i <= end;i++){
            temp[i-shift] = array[i];
        }
        int pre=0;
        int post=end-shift;
        int m = mid-shift;
        while(pre > -1 && post > m){
            if(temp[pre] > temp[post]){
                array[end] = temp[pre];
                end--;
                pre--;
            }
            if(temp[pre] < temp[post]){
                array[end] = temp[post];
                end--;
                post--;
            }
        }
        while(pre > 0){
            array[end] = temp[pre];
            end--;
            pre--;
        }
        while(post > m){
            array[end] = temp[post];
            end--;
            post--;
        }
    }
}
