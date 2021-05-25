import com.melo.wechat.dao.impl.BaseDaoImpl;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.utils.MailUtils;

import java.util.LinkedList;

public class TestFieldMapper {
    public static void main(String[] args) {
        User user = new User(1, "12");

        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        new BaseDaoImpl().fieldMapper(user, fieldNames, fieldValues);
        System.out.println(fieldNames.toString());
        System.out.println(fieldValues.toString());
    }
}
