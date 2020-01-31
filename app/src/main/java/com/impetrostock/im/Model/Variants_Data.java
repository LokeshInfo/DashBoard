package com.impetrostock.im.Model;

public class Variants_Data
{
    String VarName;
    Boolean Hlite;

    public Variants_Data(String varName, Boolean hlite) {
        VarName = varName;
        Hlite = hlite;
    }

    public String getVarName() {
        return VarName;
    }

    public void setVarName(String varName) {
        VarName = varName;
    }

    public Boolean getHlite() {
        return Hlite;
    }

    public void setHlite(Boolean hlite) {
        Hlite = hlite;
    }
}
