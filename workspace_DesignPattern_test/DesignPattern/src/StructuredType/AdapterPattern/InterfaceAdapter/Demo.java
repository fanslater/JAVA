package StructuredType.AdapterPattern.InterfaceAdapter;

import StructuredType.AdapterPattern.Voltage220;

public class Demo
{
    public static void main(String[] args)
    {
        // �Ѿ�ʵ��������
        Power5VAdapter p5va = new Power5VAdapter(new Voltage220());
        System.out.println("�������=" + p5va.output5V() + "V");
        // ֱ��ʵ������
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
        System.out.println("�������=" + pa.output5V() + "V");
    }
}
