package com.asdf.activityFrame;

/**
 * Created by User on 2017/10/24.
 */

public class ActItem {
    private Class actClass;
    private String actName;

    public ActItem(Class actClass, String actName) {
        this.actClass = actClass;
        this.actName = actName;
    }

    public Class getActClass() {
        return actClass;
    }

    public void setActClass(Class actClass) {
        this.actClass = actClass;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }
}
