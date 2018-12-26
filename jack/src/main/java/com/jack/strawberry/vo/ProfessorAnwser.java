package com.jack.strawberry.vo;

import java.util.List;

public class ProfessorAnwser {

    private List<LeaveBean> leave;

    public List<LeaveBean> getLeave() {
        return leave;
    }

    public void setLeave(List<LeaveBean> leave) {
        this.leave = leave;
    }

    public static class LeaveBean {
        /**
         * problem : 樱桃都是有什么品种？
         * answer : 樱桃的品种有：红灯，红蜜，红艳，早红，先锋，大紫拉宾斯，黄蜜，美早，龙冠，早大果，拉宾斯、那翁、梅早等
         */

        private String problem;
        private String answer;

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
