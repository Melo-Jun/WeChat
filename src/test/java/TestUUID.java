import com.melo.wechat.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUUID {

    public static void main(String[] args) {
        for(int i=0;i<20;i++) {
            String uuid = UUIDUtils.getUUID().substring(0, 4);
            Date currentTime = new Date();
            String sformat = "ssSSS";
            SimpleDateFormat formatter = new SimpleDateFormat(sformat);
            String dateString = formatter.format(currentTime);
            dateString += (int)(10*(Math.random()));
            System.out.println(uuid+dateString);
        }

    }
}
