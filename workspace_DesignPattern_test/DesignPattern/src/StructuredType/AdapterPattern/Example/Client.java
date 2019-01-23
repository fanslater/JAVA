package StructuredType.AdapterPattern.Example;

import java.util.Arrays;

public class Client
{
    public static void main(String[] args)
    {
        int[] socres =
        { 84, 76, 50, 69, 90, 92, 88, 86 };
        OperationAdapter oa = new OperationAdapter();
        int[] result = oa.Sort(socres);
        System.out.println("排序结果=" + Arrays.toString(result));
        for (int i = 50; i <= 100; i++)
        {
            if (oa.Search(socres, i) == 1)
            {
                System.out.println("查找" + i + "成功");
            }
        }
    }
}
