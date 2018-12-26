package com.jack.strawberry.vo;

import java.util.List;

public class TEST {

    private List<KnowBean> know;

    public List<KnowBean> getKnow() {
        return know;
    }

    public void setKnow(List<KnowBean> know) {
        this.know = know;
    }

    public static class KnowBean {
        /**
         * id : 1
         * url : https://v.qq.com/x/page/j0198jo72jm.html
         * title : 大连大樱桃矮化设施栽培
         * time : 2018-08-11
         */

        private int id;
        private String url;
        private String title;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
