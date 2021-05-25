//import com.melo.wechat.model.entity.User;
//import com.melo.wechat.service.impl.ChatServiceImpl;
//import com.melo.wechat.service.impl.UserServiceImpl;
//import com.melo.wechat.service.inter.ChatService;
//import com.melo.wechat.service.inter.UserService;
////import com.melo.wechat.utils.log.LogInfoUtil;
//import com.melo.wechat.utils.proxy.ServiceProxy;
//
//public class TestLog {
//
//
//    private static final UserService userService = (UserService) new ServiceProxy().getProxyInstance(new UserServiceImpl());
//    private static final ChatService chatService = (ChatService) new ServiceProxy().getProxyInstance(new ChatServiceImpl());
//
//
////    static void show01(){
////        Logger logger = LogInfoUtil.getLogger(ServiceProxy.class);
////        logger.info("111");
////    }
////    static void show02(){
////        Logger logger2 = LogInfoUtil.getLogger(ServiceProxy.class);
////        logger2.info("222");
////    }
//
//
//    public static void main(String[] args) {
////        Logger logger = LogInfoUtil.getLogger(TestLog.class);
////        logger.info("111");
////        Logger logger2 = LogInfoUtil.getLogger(TestLog.class);
////        logger2.info("111");
////        show01();
////        show02();
////        Logger logger = LogInfoUtil.getLogger(TestLog.class);
//        User user = new User();
//        user.setEmail("1158280627@qq.com");
//        user.setPassword("1111");
//        userService.login(user);
//        chatService.newFriendChat();
//    }
//}
