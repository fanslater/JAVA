package StructuredType.AdapterPattern.ObjectAdapter;

import StructuredType.AdapterPattern.Voltage220;
import StructuredType.AdapterPattern.Voltage5;

public class VoltageAdapter2 implements Voltage5
{
    private Voltage220 mVoltage220;

    public VoltageAdapter2(Voltage220 voltage220)
    {
        mVoltage220 = voltage220;
    }

    @Override
    public int output5V()
    {
        int dst = 0;
        if (null != mVoltage220)
        {
            int src = mVoltage220.output220V();
            dst = src / 44;
        }
        return dst;
    }
}
