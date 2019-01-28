package com.jc.recyclerview.itemdecoration.bean;

/**
 * Created by 江俊超 on 2019/1/23.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class RecyclerBean {


    private String roomName;
    private String floorName;
    private String stageName;

    public RecyclerBean(String roomName, String floorName, String stageName) {
        this.roomName = roomName;
        this.floorName = floorName;
        this.stageName = stageName;
    }

    public String getRoomName() {
        return floorName+"楼 - "+roomName + " 单元";
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFloorName() {
        return floorName+"楼";
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getStageName() {
        return stageName + " - 期";
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
