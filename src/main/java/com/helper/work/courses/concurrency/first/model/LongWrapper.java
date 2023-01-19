package com.helper.work.courses.concurrency.first.model;

public class LongWrapper {

    private Object key = new Object();
    private Long aLong;

    public LongWrapper(Long aLong) {
        this.aLong = aLong;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }
    public void inc() {
        synchronized (key){
            this.aLong = aLong +1;
        }
    }
}
