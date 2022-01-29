package com.mfrdev.myapplication.model;

public class Cloth {
    private float _cId;
    private float v_id;
    private boolean isAvailable;
    private String createAt;
    private String updateAt;
    private String cname;
    private String cqty;
    private String cdesc;
    private String cprice;

    @Override
    public String toString() {
        return "Cloth{" +
                "_cId=" + _cId +
                ", v_id=" + v_id +
                ", isAvailable=" + isAvailable +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", cname='" + cname + '\'' +
                ", cqty='" + cqty + '\'' +
                ", cdesc='" + cdesc + '\'' +
                ", cprice='" + cprice + '\'' +
                '}';
    }
}
