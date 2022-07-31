package gravel_sort;

/**
 * 设有顺序放置的 n 个桶，每个桶中装有一粒砾石，每粒砾石的颜色是红、白、蓝之一。要
 * 求重新安排这些砾石，使得所有红色砾石在前，所有白色砾石居中，所有蓝色砾石在后， 重
 * 新安排时对每粒砾石的颜色只能看一次，并且只允许交换操作来调整砾石的位置。
 * 注：只能看一次，时间复杂度只能是 0(n)
 */
public class GravelSort {
    /**
     * 思路：使用3个坐标i,j,k来表示3中颜色所属区域，i为最后一个红色砾石的后一位，j为最后一颗白色砾石的后一位
     * k是第一个蓝色砾石的前一位，0到i-1为红色区域，i到j-1是白色区域，k+1到最后为蓝色区域。
     *
     * 写法：初始化i,j为0，k为n-1,用j作为浮标
     * 当检索到红色砾石则与i所在的值交换，i,j后移一位（表示红，白区域的这一格数据已被处理）
     * 当检索到白色砾石则j后移一位
     * 当检索到蓝色砾石则j和k的值互换，k前移一位（j保持不动，因为换过来的有可能是红色砾石）
     * @param a
     */
    public static void gravelSort(char[] a){
        if(a == null){
            return;
        }
        if(a.length == 0){
            return;
        }
       int n = a.length;
       int i = 0;
       int j= 0;
       int k = n - 1;
       char temp;

       while(j<=k){
           if(a[j] == '红'){
                 temp = a[j];
                 a[j] = a[i];
                 a[i] = temp;
                 i++;
                 j++;
           } else if(a[j] == '白'){
                 j++;
           } else{
                 temp = a[j];
                 a[j] = a[k];
                 a[k] = temp;
                 k--;
           }
       }
    }
    public void print(char[] a){
        //打印
        for(int l = 0;l<a.length;l++){
            System.out.print(a[l]);
        }
        System.out.print("\n");
    }




    public static void main(String[] args){
        char[] example1 = {'白','红','蓝'};
        char[] example2 = {'白','白','白','红','蓝','蓝','蓝'};
        char[] example3 = {'白','蓝','蓝','红','蓝','红'};
        char[] example4 = {'红','白','蓝'};
        char[] example5 = null;
        char[] example6 = {'蓝','蓝','蓝','蓝','红','白'};
        GravelSort gravelSort = new GravelSort();
        gravelSort.gravelSort(example1);
        gravelSort.print(example1);
        gravelSort.gravelSort(example2);
        gravelSort.print(example2);
        gravelSort.gravelSort(example3);
        gravelSort.print(example3);
        gravelSort.gravelSort(example4);
        gravelSort.print(example4);
        gravelSort.gravelSort(example6);
        gravelSort.print(example6);
    }
}
