package StructuredType.AdapterPattern.ClassAdapter;

import StructuredType.AdapterPattern.Voltage5;

/**
 * ���ܣ�Client�ࣺ�ֻ� .��Ҫ5V��ѹ
 */
public class Mobile
{
    public static void main(String[] args)
    {
        Voltage5 v5 = new VoltageAdapter();
        System.out.println("�������" + v5.output5V() + "V");
    }
}
/*
 * Java���ֵ��̳еĻ��ƣ�������Ҫ�̳е��Ҹ��˶���̫ϲ���� ��������������Ҫ�̳�src����һ������һ��ȱ�㣬 ��Ϊ��Ҫ��dst�����ǽӿڣ���һ��������; ��src��ķ�����Adapter�ж��ᱩ¶������Ҳ������ʹ�õĳɱ��� ��ͬ��������̳���src�࣬���������Ը���������дsrc��ķ�����ʹ��Adapter���������ǿ�ˡ�
 */