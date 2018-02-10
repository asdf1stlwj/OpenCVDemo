package com.asdf.list;

/**
 * Created by hasee on 2018/2/10.
 */
public class CommonData {
    private long id;
    private String command;
    private String name;

    public CommonData(String command,long id){
        this.command=command;
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
