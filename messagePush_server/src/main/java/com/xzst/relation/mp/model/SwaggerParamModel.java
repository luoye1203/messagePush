package com.xzst.relation.mp.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


//@ApiModel(value="查询参数",description="查询参数")
public class SwaggerParamModel  {

    @ApiModelProperty(value="用户名",example="xingguo")

    private String userName;

    @ApiModelProperty(value="年龄", example="10" ,notes = "必虚")
    private Integer age;

    private boolean isVip;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    @Override
    public String toString() {
        return "SwaggerParamModel{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", isVip=" + isVip +
                '}';
    }
}
