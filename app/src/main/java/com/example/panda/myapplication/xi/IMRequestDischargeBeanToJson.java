package com.example.panda.myapplication.xi;


import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.port.InterfaceName;
import com.example.panda.myapplication.utils.GsonUtility;

public class IMRequestDischargeBeanToJson extends BaseBean {


    public static String iMRequestDischargeBeanToJson(String bt) {
        IMSendDischargeBean bean = new IMSendDischargeBean(InterfaceName.CONTROL_DISCHARGE_PORT, "0", "success", bt);
        String str = GsonUtility.bean2Json(bean);
        return str;
    }


    /**
     * 内部类
     */
    static class IMSendDischargeBean extends BaseBean {
        private String bt;//业务类型，0 1 2分别表示左中右三个出口
        private boolean operation;//操作方式，false表示关，true表示开

        private IMSendDischargeBean(String mt, String state, String msg, String bt) {
            this.bt = bt;
            this.operation = operation;
            this.mt = mt;
            this.state = state;
            this.msg = msg;
        }
    }
}
