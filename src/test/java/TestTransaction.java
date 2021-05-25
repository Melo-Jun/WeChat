import com.melo.wechat.model.entity.LikeList;
import com.melo.wechat.service.impl.LikeListServiceImpl;
import com.melo.wechat.service.inter.LikeListService;
import com.melo.wechat.utils.proxy.ServiceProxy;
import com.melo.wechat.utils.proxy.TrancationProxy;

public class TestTransaction {

    /**
     * 代理对象类
     */
    static LikeListService likeListService = TrancationProxy.getProxyInstance(LikeListServiceImpl.class);

    public static void main(String[] args) {
        LikeList likeList = new LikeList();
        likeList.setId(1);
        likeList.setUserId(0);
        likeList.setMomentId(18);
        likeListService.like(likeList);
    }
}
