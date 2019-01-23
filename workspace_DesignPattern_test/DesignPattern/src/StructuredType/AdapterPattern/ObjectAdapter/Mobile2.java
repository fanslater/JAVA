package StructuredType.AdapterPattern.ObjectAdapter;

import StructuredType.AdapterPattern.Voltage220;

/**
 * ���ܣ�Client�ࣺ�ֻ� .��Ҫ5V��ѹ
 */
public class Mobile2
{
    public static void main(String[] args)
    {        
        VoltageAdapter2 voltageAdapter2 = new VoltageAdapter2(new Voltage220());
        System.out.println("�������"+ voltageAdapter2.output5V() +"V");
    }
}
/*
 * ����������������������ʵ����ͬһ��˼�룬ֻ����ʵ�ַ�ʽ��ͬ�� ���ݺϳɸ���ԭ����ϴ��ڼ̳У� �����������������������̳�src�ľ��������⣬Ҳ����ǿ��dst�����ǽӿڡ� ͬ������ʹ�óɱ����ͣ�����
 * 
 * ����װ����ģʽ��ѧʱ���ܻ�Ū�죬����Ҫ���壬װ�����Ƕ�src��װ�Σ�ʹ���ߺ��޲����src�Ѿ���װ���ˣ�ʹ�����÷����䣩�� ������������Ժ�ʹ���ߵ��÷����Ǳ�ġ� ����װ�����÷��� setSrc->setSrc�������������÷���setSrc->setAdapter.)
 * 
 */