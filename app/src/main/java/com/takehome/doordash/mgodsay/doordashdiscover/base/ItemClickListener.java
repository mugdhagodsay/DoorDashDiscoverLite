package com.takehome.doordash.mgodsay.doordashdiscover.base;

import com.takehome.doordash.mgodsay.doordashdiscover.model.BaseResponse;

public interface ItemClickListener {

    /**
     * Event called with an item in the componentView is tapped
     *
     * @param dataModel instance of data model that represents the item
     * @return If this click event was handled
     */
    boolean onItemClick(BaseResponse dataModel);
}
