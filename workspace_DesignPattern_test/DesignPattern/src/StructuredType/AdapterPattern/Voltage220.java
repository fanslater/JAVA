package StructuredType.AdapterPattern;

/**
 * ���ܣ�src��: �����е�220V��ѹ
 */
public class Voltage220
{
    public int output220V()
    {
        int src = 220;
        System.out.println("ԭ����" + src + "V");
        return src;
    }
}
