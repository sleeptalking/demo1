package com.hflh.demo.bean;



import java.util.List;

/**
 * Created by zx on 2017/3/21.
 */

public class YuQingGaoWeiBean {

    public Data data;
    public int code;

    public static class Data {
        public int currentPageSize;
        public int totalCount;
        public int currentPageNo;
        public int totalPageCount;
        public boolean empty;
        public List<GaoWeiNews> dataList;

        @Override
        public String toString() {
            return "Data{" +
                    "currentPageSize=" + currentPageSize +
                    ", totalCount=" + totalCount +
                    ", currentPageNo=" + currentPageNo +
                    ", totalPageCount=" + totalPageCount +
                    ", empty=" + empty +
                    ", dataList=" + dataList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "YuQingGaoWeiBean{" +
                "data=" + data +
                ", code=" + code +
                '}';
    }
}

