package com.mfrdev.myapplication.model;

import android.widget.SearchView;

import java.io.Serializable;

public class GlossaryResponseBody implements Serializable {
    private int _gId;
    private int v_id;
    private boolean isAvailable;
    private String createAt;
    private String updateAt;
    private String gname;
    private String gprice;
    private String gdesc;
    private String gqty;

    public int get_gId() {
        return _gId;
    }

    public void set_gId(int _gId) {
        this._gId = _gId;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGprice() {
        return gprice;
    }

    public void setGprice(String gprice) {
        this.gprice = gprice;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public String getGqty() {
        return gqty;
    }

    public void setGqty(String gqty) {
        this.gqty = gqty;
    }
}
