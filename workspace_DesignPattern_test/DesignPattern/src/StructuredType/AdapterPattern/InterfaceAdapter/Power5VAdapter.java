package StructuredType.AdapterPattern.InterfaceAdapter;

import StructuredType.AdapterPattern.Voltage220;

public class Power5VAdapter extends PowerAdapter
{
    public Power5VAdapter(Voltage220 ac220)
    {
        super(ac220);
    }

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
}
