package com.vargancys.vargancysshop.module.home.home.data;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class SeckillInfo {
    private List<SeckillItem> seckillItems;
    private String start_time;
    private String end_time;

    public List<SeckillItem> getSeckillItems() {
        return seckillItems;
    }

    public void setSeckillItems(List<SeckillItem> seckillItems) {
        this.seckillItems = seckillItems;
    }

    public class SeckillItem{
        private String url;
        private String title;
        private String title_old;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_old() {
            return title_old;
        }

        public void setTitle_old(String title_old) {
            this.title_old = title_old;
        }
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }

}
