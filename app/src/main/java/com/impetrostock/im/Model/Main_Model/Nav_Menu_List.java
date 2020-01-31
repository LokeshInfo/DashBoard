package com.impetrostock.im.Model.Main_Model;

public class Nav_Menu_List
{
    public String menuName;
    public boolean hasChildren, isGroup;

    public Nav_Menu_List(String menuName, boolean isGroup, boolean hasChildren) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }
}
