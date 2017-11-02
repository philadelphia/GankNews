package com.example.app.mvp;

import com.example.app.api.ApiService;
import com.example.app.bean.CategoryEntity;
import com.example.app.bean.GankDailyDataEntity;
import com.example.app.common.RetrofitUtil;
import com.example.app.utils.RxsRxSchedulers;

import rx.Observable;


/**
 * Author Tao.ZT.Zhang
 * Date   2017/10/30
 */

public class Model implements MvpContract.IModel {
    @Override
    public Observable<CategoryEntity> fetchData(String category, int dataCount, int pageCount) {
        return RetrofitUtil.getInstance().getRetrofit().create(ApiService.class).getSomeCategory(category, dataCount, pageCount).compose(RxsRxSchedulers.<CategoryEntity>io_main());
    }


}
