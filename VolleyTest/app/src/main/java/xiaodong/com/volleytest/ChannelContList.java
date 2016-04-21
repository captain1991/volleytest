package xiaodong.com.volleytest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yxd on 2016/4/14.
 */
public class ChannelContList implements Serializable{
    int resultCode;
    String resultMsg;
    long systemTime;
    int nodeId;
    Date date;
    List<ChannelCont> contList;

    public void setContList(List<ChannelCont> contList) {
        this.contList = contList;
    }

    public List<ChannelCont> getContList() {

        return contList;
    }

    public Date getDate(){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getSystemTime());
        date = c.getTime();
        return date;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setSystemTime(long systemTime) {
        this.systemTime = systemTime;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public int getNodeId() {
        return nodeId;
    }
}
