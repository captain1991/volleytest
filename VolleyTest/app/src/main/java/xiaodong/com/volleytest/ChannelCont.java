package xiaodong.com.volleytest;

import java.io.Serializable;

/**
 * Created by yxd on 2016/4/14.
 */
public class ChannelCont implements Serializable {

    int contId;
    String name;
    String pubTime;

    public int getContId() {
        return contId;
    }

    public String getName() {
        return name;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setContId(int contId) {
        this.contId = contId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }
}
