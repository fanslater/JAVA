package StructuredType.AdapterPattern.InterfaceAdapter;

import StructuredType.AdapterPattern.Voltage220;

public class Demo
{
    public static void main(String[] args)
    {
        // 已经实现了子类
        Power5VAdapter p5va = new Power5VAdapter(new Voltage220());
        System.out.println("最终输出=" + p5va.output5V() + "V");
        // 直接实现子类
        PowerAdapter pa = new PowerAdapter(new Voltage220())
        {
            @Override
            public int output5V()
            {
                int output = 0;
                if (mAC220 != null)
                {
                    output = mAC220.output220V() / 44;
                }
                return output;
            }
        };
        System.out.println("最终输出=" + pa.output5V() + "V");
    }
}
