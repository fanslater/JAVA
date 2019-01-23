package StructuredType.AdapterPattern.ClassAdapter;

import StructuredType.AdapterPattern.Voltage220;
import StructuredType.AdapterPattern.Voltage5;

public class VoltageAdapter extends Voltage220 implements Voltage5
{
    @Override
    public int output5V()
    {
        int src = output220V();
        int dst = src / 44;
        return dst;
    }
}
