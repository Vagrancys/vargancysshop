package com.vargancys.vargancysshop.widget.section;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/27
 * version:1.0
 */
public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int VIEW_TYPE_HEADER=0;
    public final static int VIEW_TYPE_FOOTER = 1;
    public final static int VIEW_TYPE_ITEM_LOADED = 2;
    private LinkedHashMap<String,Section> sections;
    private HashMap<String,Integer> sectionViewTypeNumbers;
    private int viewTypeCount=0;
    private final static int VIEW_TYPE_QTY=3;

    public SectionedRecyclerViewAdapter(){
        sections=new LinkedHashMap<>();
        sectionViewTypeNumbers=new HashMap<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        View view;
        for (Map.Entry<String,Integer> entry:sectionViewTypeNumbers.entrySet()){
            if(viewType>=entry.getValue()&&viewType<entry.getValue()+VIEW_TYPE_QTY){
                Section section=sections.get(entry.getKey());
                int sectionViewType=viewType-entry.getValue();
                switch (sectionViewType){
                    case VIEW_TYPE_HEADER:{
                        Integer resId=section.getHeaderResourceId();
                        if(resId==null){
                            throw new NullPointerException("Missing 'header' resource id");
                        }
                        view= LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);
                        viewHolder=section.getHeaderViewHolder(view);
                        break;
                    }
                    case VIEW_TYPE_FOOTER:{
                        Integer resId=section.getFooterResourceId();
                        if(resId==null){
                            throw new NullPointerException("Missing 'footer' resource id");
                        }
                        view= LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);
                        viewHolder=section.getFooterViewHolder(view);
                        break;
                    }
                    case VIEW_TYPE_ITEM_LOADED:{
                        view= LayoutInflater.from(parent.getContext()).inflate(section.getItemResourceId(),parent,false);
                        viewHolder=section.getItemViewHolder(view);
                        break;
                    }
                    default:
                        throw new IllegalArgumentException("Invalid viewType");
                }
            }
        }
        return viewHolder;
    }

    public void addSection(String tag, Section section) {
        this.sections.put(tag, section);
        this.sectionViewTypeNumbers.put(tag, viewTypeCount);
        viewTypeCount += VIEW_TYPE_QTY;
    }

    public String addSection(Section section) {
        String tag = UUID.randomUUID().toString();
        addSection(tag, section);
        return tag;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int currentPos=0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section=entry.getValue();
            if(!section.isVisible()) continue;
            int sectionTotal=section.getSectionItemTotal();
            if(position>=currentPos &&position<=(currentPos+sectionTotal-1)){
                if(section.isHasHeader()){
                    if (position==currentPos){
                        getSectionForPosition(position).onBindHeaderViewHolder(holder);
                        return;
                    }
                }
                if(section.isHasFooter()){
                    if(position==(currentPos+sectionTotal-1)){
                        getSectionForPosition(position).onBindFooterViewHolder(holder);
                        return;
                    }
                }
                getSectionForPosition(position).onBindContentViewHolder(holder,getSectionPosition(position));
                return;
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {
        int count=0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section=entry.getValue();
            if(!section.isVisible()) continue;
            count +=section.getSectionItemTotal();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int currentPos=0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section=entry.getValue();
            if(!section.isVisible())continue;
            int sectionTotal=section.getSectionItemTotal();
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                int viewType=sectionViewTypeNumbers.get(entry.getKey());
                if(section.isHasHeader()){
                    if(position==currentPos){
                        return viewType;
                    }
                }
                if(section.isHasFooter()){
                    if(position==(currentPos+sectionTotal-1)){
                        return viewType+1;
                    }
                }
                switch (section.getState()){
                    case LOADED:
                        return viewType+2;
                    default:
                        throw new IllegalStateException("Invalid state");
                }
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionItemViewType(int position){
        int viewType=getItemViewType(position);
        return viewType%VIEW_TYPE_QTY;
    }

    public Section getSectionForPosition(int position){
        int currentPos=0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section=entry.getValue();
            if(!section.isVisible()) continue;
            int sectionTotal=section.getSectionItemTotal();
            if(position>=currentPos&&position<=(currentPos+sectionTotal-1)){
                return section;
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionPosition(int position){
        int currentPos=0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section=entry.getValue();
            if( !section.isVisible()) continue;
            int sectionTotal=section.getSectionItemTotal();
            if(position>=currentPos&&position<=(currentPos+sectionTotal-1)){
                return position-currentPos-(section.isHasHeader()?1:0);
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder{
        public EmptyViewHolder(View itemView) {super(itemView);}
    }
}
