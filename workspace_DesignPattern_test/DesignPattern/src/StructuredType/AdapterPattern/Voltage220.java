package StructuredType.AdapterPattern;

/**
 * 介绍：src类: 我们有的220V电压
 */
public class Voltage220
{
    public int output220V()
    {
        int src = 220;
        System.out.println("原来是" + src + "V");
        return src;
    }
}
