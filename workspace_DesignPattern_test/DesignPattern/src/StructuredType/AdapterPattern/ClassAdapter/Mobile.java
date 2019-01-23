package StructuredType.AdapterPattern.ClassAdapter;

import StructuredType.AdapterPattern.Voltage5;

/**
 * 介绍：Client类：手机 .需要5V电压
 */
public class Mobile
{
    public static void main(String[] args)
    {
        Voltage5 v5 = new VoltageAdapter();
        System.out.println("最终输出" + v5.output5V() + "V");
    }
}
/*
 * Java这种单继承的机制，所有需要继承的我个人都不太喜欢。 所以类适配器需要继承src类这一点算是一个缺点， 因为这要求dst必须是接口，有一定局限性; 且src类的方法在Adapter中都会暴露出来，也增加了使用的成本。 但同样由于其继承了src类，所以它可以根据需求重写src类的方法，使得Adapter的灵活性增强了。
 */