package StructuredType.AdapterPattern.Example;

public class OperationAdapter implements ScoreOperation
{
    // 定义适配者QuickSortClass对象
    private QuickSortClass sortObj;
    // 定义适配者BinarySearchClass对象
    private BinarySearchClass searchObj;

    public OperationAdapter()
    {
        sortObj = new QuickSortClass();
        searchObj = new BinarySearchClass();
    }

    @Override
    public int Search(int[] array, int key)
    {
        return searchObj.BinarySearch(array, key);
    }

    @Override
    public int[] Sort(int[] array)
    {
        return sortObj.QuickSort(array);
    }
}
