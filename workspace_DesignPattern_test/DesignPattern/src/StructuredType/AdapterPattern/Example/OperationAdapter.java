package StructuredType.AdapterPattern.Example;

public class OperationAdapter implements ScoreOperation
{
    // ����������QuickSortClass����
    private QuickSortClass sortObj;
    // ����������BinarySearchClass����
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
