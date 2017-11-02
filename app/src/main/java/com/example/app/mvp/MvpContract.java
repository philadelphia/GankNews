package com.example.app.mvp;


import com.example.app.bean.CategoryEntity;
import com.example.app.bean.GankDailyDataEntity;

import java.util.List;

import rx.Observable;


/**
 * Author Tao.ZT.Zhang
 * Date   2017/10/30
 */

public class MvpContract {
    public  interface IView{
       void onLoadDataSuccess(List<CategoryEntity.ResultsBean> dataList);
       void onLoadDataFailed();

    }
    public  interface IModel{
         Observable<CategoryEntity> fetchData(String category, int dataCount, int pageCount) ;
    }
}
