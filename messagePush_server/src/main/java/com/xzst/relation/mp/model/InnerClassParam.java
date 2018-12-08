package com.xzst.relation.mp.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class InnerClassParam {

    @ApiModelProperty(value="id",example="10")
    private String id;

    private List<InnerParam> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<InnerParam> getList() {
        return list;
    }

    public void setList(List<InnerParam> list) {
        this.list = list;
    }

    public static class InnerParam{
        @ApiModelProperty(value="age",example="20")
        private String  age;
        @ApiModelProperty(value="name",example="lht")
        private String  name;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "InnerParam{" +
                    "age='" + age + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "InnnerClassParam{" +
                "id='" + id + '\'' +
                ", list=" + list +
                '}';
    }
}
