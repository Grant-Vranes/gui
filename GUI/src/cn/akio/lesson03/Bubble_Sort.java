package cn.akio.lesson03;

import java.awt.*;
import java.util.Arrays;

/**
 * @author Akio
 * @ClassName Bubble_Sort
 * @Description 冒泡排序
 * @Date 2021/7/12 19:58
 */
public class Bubble_Sort {
    public static void main(String[] args) {
        double[] d = {12.6, 34.2, 11.45};
        System.out.println(Arrays.toString(Bubble(d)));

        int[] arr = {10, 56, 34, 57, 78};
        System.out.println(Arrays.toString(Bubble(arr)));


        //冒泡排序法，从数组“顶端【首部】”每次向下沉积出一个值（与下一个值做比较）  ***大数下沉
        int[] array = new int[]{5, 3, 2, 4, 1};//定义一个数组array
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
//                    array[j] = array[j] + array[j + 1];
//                    array[j + 1] = array[j] - array[j + 1];
//                    array[j] = array[j] - array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
        for (int v : array) {//遍历输出数组中的所有值
            System.out.print(v + "\t");
        }
    }

    private static double[] Bubble(double[] doubles) {
        for (int i = 0; i < doubles.length - 1; i++) {
            for (int j = 0; j < doubles.length - i - 1; j++) {
                if (doubles[j] > doubles[j + 1]) {
                    double temp = doubles[j];
                    doubles[j] = doubles[j + 1];
                    doubles[j + 1] = temp;
                }
            }
        }
        return doubles;
    }

    private static int[] Bubble(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < i; j++) {
                if (ints[j] > ints[j + 1]) {
                    ints[j] = ints[j] ^ ints[j + 1];
                    ints[j + 1] = ints[j] ^ ints[j + 1];
                    ints[j] = ints[j] ^ ints[j + 1];
                }
            }
        }
        return ints;
    }
}

