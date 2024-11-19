package com.sriram9217.demo1.entity;

public class helloWorldBean {
    public String message;

    public helloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString(){
        return String.format("HelloWorldBean [message = %s]", message);
    }
}
