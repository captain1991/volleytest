package xiaodong.com.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements HRCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getData(View v){
        Map<String,String> map = new HashMap<String,String>();
        map.put("n","25953");
        map.put("WD-UUID","144789312955962");
        map.put("WD-CLIENT-TYPE","04");
        map.put("WD-UA","PPC5_480x800_W");
        map.put("WD-VERSION","3.2.9");
        map.put("WD-CHANNEL","thepapercn");
        map.put("WD-RESOLUTION","480*800");
        map.put("userId","981775");
        map.put("WD-TOKEN", "10ff419fdd0f4226dbb715b966df7c83");
//        HttpProxy.getDataByGet(MainActivity.this, "http://app.thepaper.cn/clt/jsp/v3/channelContList.jsp?", "11", map, this, 1);
        HttpProxy.getObjectByGet(MainActivity.this, "http://app.thepaper.cn/clt/jsp/v3/channelContList.jsp?", "11", map, this, 1, ChannelContList.class);
//        HttpProxy.getObjectByPost(MainActivity.this, "http://app.thepaper.cn/clt/jsp/v3/channelContList.jsp?", "11", map, this, 1, ChannelContList.class);
//        HttpProxy.getDataByPost(MainActivity.this, "http://app.thepaper.cn/clt/jsp/v3/channelContList.jsp?", "11", map, this, 1);
    }

    @Override
    public void httpResponse(int resultCode, Object... responseData) {
        Log.i("success", "postsucess");
    }


    @Override
    public void httpResponse(int resultCode, Object responseData) {
        if (resultCode ==101){
//            ChannelContList contList = (ChannelContList) responseData;
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String s = sdf.format(contList.getDate());
//            for (ChannelCont channelCont:contList.getContList()){

//            }
            Log.i("success", "postsucess");
        }
    }
}
