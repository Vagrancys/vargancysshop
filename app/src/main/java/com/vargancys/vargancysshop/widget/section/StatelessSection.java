package com.vargancys.vargancysshop.widget.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/27
 * version:1.0
 */
public abstract class StatelessSection extends Section{
    public StatelessSection(int itemResourceId){
        super();
        this.itemResourceId=itemResourceId;
    }
    public StatelessSection(int headerResourceId,int itemResourceId){
        this(itemResourceId);
        this.headerResourceId=headerResourceId;
        this.hasHeader=true;
    }

    public StatelessSection(int headerResourceId,int footerResourceId,int itemResourceId){
        this(headerResourceId, itemResourceId);
        this.footerResourceId=footerResourceId;
        this.hasFooter=true;
    }

}
