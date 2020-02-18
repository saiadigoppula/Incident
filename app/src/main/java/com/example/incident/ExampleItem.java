package com.example.incident;

public class ExampleItem {
    private String mCreator;

    private String msys_id;

    public ExampleItem(String creator ,String sys_id) {

        mCreator = creator;

        msys_id = sys_id;
    }

    public String getCreator() {
        return mCreator;
    }


    public String getSysid(){
        return msys_id;
    }
}