package com.mfrdev.myapplication.model;

import java.io.Serializable;

public class GlossaryRequestBody implements Serializable {
    private int v_id;
    private boolean isAvailable;
    private String gname;
    private String gdesc;
    private String gqty;
    private String gprice;

    public GlossaryRequestBody(int v_id, boolean isAvailable, String gname, String gdesc, String gqty, String gprice) {
        this.v_id = v_id;
        this.isAvailable = isAvailable;
        this.gname = gname;
        this.gdesc = gdesc;
        this.gqty = gqty;
        this.gprice = gprice;
    }
}
