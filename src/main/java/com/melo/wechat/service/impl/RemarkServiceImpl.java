package com.melo.wechat.service.impl;

import com.melo.wechat.dao.impl.RemarkDaoImpl;
import com.melo.wechat.dao.impl.UserDaoImpl;
import com.melo.wechat.dao.inter.RemarkDao;
import com.melo.wechat.dao.inter.UserDao;
import com.melo.wechat.model.builder.RemarkVOBuilder;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Remark;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.model.vo.RemarkVO;
import com.melo.wechat.service.inter.RemarkService;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;

/**
 * @Description: 评论业务逻辑实现类
 * @author: Jun
 * @date: 19:36 2021/5/16
 */
public class RemarkServiceImpl implements RemarkService {



    /**
     * 代理对象类
     */
    RemarkDao remarkDao = DaoProxy.getProxyInstance(RemarkDaoImpl.class);
    UserDao userDao = DaoProxy.getProxyInstance(UserDaoImpl.class);

    @Override
    public ServiceResult showRemark(Integer momentId) {
        ServiceResult result = new ServiceResult();
        Remark remark = new Remark();
        remark.setMomentId(momentId);
        LinkedList<RemarkVO> remarkVos = new LinkedList<>();
        //根据momentId列出相关remark
        LinkedList<Remark> remarks = remarkDao.listRemarkByMomentId(remark);
        for(Remark tempRemark:remarks){
            //根据remark对应的userId构建视图层对象
            User user = userDao.getUserById(tempRemark.getUserId());
            RemarkVO remarkVO=new RemarkVOBuilder().setUserName(user.getUserName())
                    .setAvatar(user.getAvatar())
                    .setContent(tempRemark.getContent())
                    .setGmtCreate(tempRemark.getGmtCreate()).build();
            remarkVos.add(remarkVO);
        }
        if(!remarkVos.isEmpty()){
            result.setFlag(true);
        }
        result.setData(remarkVos);
        return result;
    }

    @Override
    public ServiceResult newRemark(Remark remark) {
        ServiceResult result = new ServiceResult();
        if(remarkDao.insert(remark)==1){
            result.setFlag(true);
        }
        return result;
    }

}
