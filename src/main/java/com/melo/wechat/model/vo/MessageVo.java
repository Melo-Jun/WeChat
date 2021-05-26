package com.melo.wechat.model.vo;

import com.melo.wechat.model.entity.Message;

import java.util.List;

/**
 * @Description: 聊天记录分页数据封装对象
 * @author: Jun
 * @date: 17:27 2021/5/25
 */
public class MessageVo {
    /**
     * 总聊天记录条数
     */
    private Long totalCount;
    /**
     * 总页码
     */
    private Long totalPage;
    /**
     * 每页数据
     */
    private List<Message> list;
    /**
     * 当前页数
     */
    private int currentPage;
    /**
     * 每页显示条数
     */
    private int rows;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}


