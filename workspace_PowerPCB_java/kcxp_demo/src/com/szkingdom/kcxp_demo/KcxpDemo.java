package com.szkingdom.kcxp_demo;

import java.util.Arrays;
import java.util.HashMap;

import com.szkingdom.kcxp.KCXPAPI;
import com.szkingdom.kcxp.KCXPC;
import com.szkingdom.kcxp.KCXPGMO;
import com.szkingdom.kcxp.KCXPMD;
import com.szkingdom.kcxp.KCXPOD;
import com.szkingdom.kcxp.KCXPPMO;
import com.szkingdom.kcxp.KCXPPint;

public class KcxpDemo
{
    // config
    private String strIp;
    private String strPort;
    private String strUserName;
    private String strPassword;
    private String strActiveQueueName;
    // kcxpcli
    private KCXPAPI kcxpHandle = new KCXPAPI();
    private HashMap<String, KCXPPint> mpActiveQueues = new HashMap<String, KCXPPint>();

    public static void main(String[] args)
    {
        KcxpDemo kd = new KcxpDemo();
        kd.setConfig("127.0.0.1", "21000", "KCXP00", "888888", "zhoufan");
        kd.connect();
        kd.openActiveQueue();
        kd.writeData("zhoufan 1234567890 abcdefg");
        StringBuffer sbBuf = new StringBuffer();
        kd.readData(sbBuf);
        kd.clossActiveQueue();
        kd.disconnect();
    }

    public void setConfig(String strIp, String strPort, String strUserName, String strPassword, String strActiveQueueName)
    {
        this.strIp = strIp;
        this.strPort = strPort;
        this.strUserName = strUserName;
        this.strPassword = strPassword;
        this.strActiveQueueName = strActiveQueueName;
    }

    public int connect()
    {
        KCXPPint CompCode = new KCXPPint();
        KCXPPint Reason = new KCXPPint();
        kcxpHandle.Connx(strIp, Integer.parseInt(strPort), strUserName, strPassword, CompCode, Reason);
        if (CompCode.x != KCXPC.KCXP_CC_OK)
        {
            StringBuilder sbBuf = new StringBuilder();
            kcxpHandle.ErrInfo(Reason.x, sbBuf, false);
            System.out.println(String.format("kcxp kcxpConnect error. CompCode=[%d] ReasonCode=[%d] ReasonMsg=[%s]", CompCode.x, Reason.x, sbBuf.toString()));
            return -1;
        }
        System.out.println("kcxp connect success");
        return 0;
    }

    public int disconnect()
    {
        KCXPPint CompCode = new KCXPPint();
        KCXPPint Reason = new KCXPPint();
        int options = KCXPC.KCXP_CO_NONE;
        kcxpHandle.Disconn(options, CompCode, Reason);
        if (CompCode.x != KCXPC.KCXP_CC_OK)
        {
            StringBuilder sbBuf = new StringBuilder();
            kcxpHandle.ErrInfo(Reason.x, sbBuf, false);
            System.out.println(String.format("kcxp disConnect error. CompCode=[%d] ReasonCode=[%d] ReasonMsg=[%s]", CompCode.x, Reason.x, sbBuf.toString()));
            return -1;
        }
        System.out.println("kcxp disconnect success");
        return 0;
    }

    public int openActiveQueue()
    {
        KCXPPint Hobj = new KCXPPint();
        if (mpActiveQueues.containsKey(strActiveQueueName))
        {
            Hobj = (KCXPPint) mpActiveQueues.get(strActiveQueueName);
            return 0;
        }
        KCXPPint CompCode = new KCXPPint();
        KCXPPint Reason = new KCXPPint();
        KCXPOD od = new KCXPOD();
        od.iObjectType = KCXPC.KCXP_OT_Q;
        od.strObjectName = strActiveQueueName;
        int options = KCXPC.KCXP_OO_AS_Q_DEF | KCXPC.KCXP_OO_INPUT | KCXPC.KCXP_OO_OUTPUT;
        kcxpHandle.Open(od, options, Hobj, CompCode, Reason);
        if (CompCode.x != KCXPC.KCXP_CC_OK)
        {
            StringBuilder sbBuf = new StringBuilder();
            kcxpHandle.ErrInfo(Reason.x, sbBuf, false);
            System.out.println(String.format("kcxp openActiveQueue=[%s] error. CompCode=[%d] ReasonCode=[%d] ReasonMsg=[%s]", strActiveQueueName, CompCode.x, Reason.x, sbBuf.toString()));
            return -1;
        }
        mpActiveQueues.put(strActiveQueueName, Hobj);
        System.out.println(String.format("kcxp openActiveQueue=[%s] success", strActiveQueueName));
        return 0;
    }

    public int clossActiveQueue()
    {
        if (mpActiveQueues.containsKey(strActiveQueueName) == false)
        {
            System.out.println(String.format("kcxp closeActiveQueue error. [%s] no found.", strActiveQueueName));
            return -1;
        }
        KCXPPint Hobj = (KCXPPint) mpActiveQueues.get(strActiveQueueName);
        KCXPPint CompCode = new KCXPPint();
        KCXPPint Reason = new KCXPPint();
        int options = KCXPC.KCXP_CO_NONE;
        kcxpHandle.Close(Hobj, options, CompCode, Reason);
        if (CompCode.x != KCXPC.KCXP_CC_OK)
        {
            StringBuilder sbBuf = new StringBuilder();
            kcxpHandle.ErrInfo(Reason.x, sbBuf, false);
            System.out.println(String.format("kcxp closeActiveQueue error. CompCode=[%d] ReasonCode=[%d] ReasonMsg=[%s]", CompCode.x, Reason.x, sbBuf.toString()));
            return -1;
        }
        mpActiveQueues.remove(strActiveQueueName);
        System.out.println(String.format("kcxp closeActiveQueue=[%s] success", strActiveQueueName));
        return 0;
    }

    public int readData(StringBuffer sbData)
    {
        if (sbData == null)
        {
            return -1;
        }
        if (mpActiveQueues.containsKey(strActiveQueueName) == false)
        {
            return -1;
        }
        KCXPPint Hobj = (KCXPPint) mpActiveQueues.get(strActiveQueueName);
        KCXPPint CompCode = new KCXPPint();
        KCXPPint Reason = new KCXPPint();
        KCXPMD msgDesc = new KCXPMD();
        KCXPPint len = new KCXPPint();
        KCXPGMO gmo = new KCXPGMO();
        try
        {
            gmo.Options = KCXPC.KCXP_GMO_WAIT | KCXPC.KCXP_GMO_FAIL_IF_QUIESCING | KCXPC.KCXP_GMO_GET;
            gmo.WaitInterval = 1;
            byte[] byteBuf = new byte[10240];
            Arrays.fill(byteBuf, (byte) 0);
            kcxpHandle.Get(Hobj.x, msgDesc, gmo, byteBuf.length, byteBuf, len, CompCode, Reason);
            if (CompCode.x != KCXPC.KCXP_CC_OK)
            {
                if (Reason.x != KCXPC.KCXP_RC_NO_MSG_AVAILABLE)
                {
                    StringBuilder sbBuf = new StringBuilder();
                    kcxpHandle.ErrInfo(Reason.x, sbBuf, false);
                    System.out.println(String.format("kcxp readData error! CompCode=[%d] ReasonCode=[%d] ReasonMsg=[%s] XpQueue=[%s].", CompCode.x, Reason.x, sbBuf.toString(), strActiveQueueName));
                    return -1;
                }
                return 2011;
            }
            String strMsg = new String(byteBuf, "GBK");
            strMsg = strMsg.substring(0, len.x).trim();
            sbData.append(strMsg);
        }
        catch (Exception e)
        {
            System.out.println(String.format("kcxp readData fail! XpQueue=[%s] ExceptionMsg=[%s].", strActiveQueueName, e.toString()));
            return -1;
        }
        System.out.println(String.format("kcxp readData success. data=[%s]", sbData.toString()));
        return 0;
    }

    public int writeData(String strData)
    {
        if (strData == null || strData.length() == 0)
        {
            return -1;
        }
        if (mpActiveQueues.containsKey(strActiveQueueName) == false)
        {
            return -1;
        }
        KCXPPint Hobj = (KCXPPint) mpActiveQueues.get(strActiveQueueName);
        KCXPPint compCode = new KCXPPint();
        KCXPPint reason = new KCXPPint();
        KCXPPMO pmo = new KCXPPMO();
        KCXPMD MsgDesc = new KCXPMD();
        KCXPOD MsgOd = new KCXPOD();
        // 超过32k就需要设置md的cbMsgFlags |=KCXP_MF_SEGMENTATION_ALLOWED，否则会得到2009的错误
        try
        {
            MsgDesc.cbMsgType = KCXPC.KCXP_MT_DATAGRAM;
            MsgDesc.iPriority = 0x7;
            MsgDesc.iLifeTime = 200000;
            MsgDesc.iDataLength = strData.getBytes("GBK").length;
            MsgOd.iObjectType = KCXPC.KCXP_OT_ROUTE;
            pmo.Options = KCXPC.KCXP_PMO_NONE;
            pmo.Timeout = -1;
            kcxpHandle.Put(Hobj.x, MsgDesc, pmo, MsgDesc.iDataLength, strData.getBytes("GBK"), compCode, reason);
        }
        catch (Exception e)
        {
            System.out.println(String.format("kcxp writeData error. ExceptionMsg=[%s]", e.toString()));
            return -1;
        }
        if (compCode.x != KCXPC.KCXP_CC_OK)
        {
            StringBuilder sbBuf = new StringBuilder();
            kcxpHandle.ErrInfo(reason.x, sbBuf, false);
            System.out.println(String.format("kcxp writeData error. CompCode=[%d] ReasonCode=[%d] ReasonMsg=[%s]", compCode.x, reason.x, sbBuf.toString()));
            return -1;
        }
        System.out.println(String.format("kcxp writeData success. data=[%s]", strData));
        return 0;
    }
}
