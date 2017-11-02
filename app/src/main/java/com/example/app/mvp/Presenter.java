package com.example.app.mvp;

import android.util.Log;

import com.example.app.bean.CategoryEntity;

import rx.Observable;
import rx.functions.Action1;


/**
 * Author Tao.ZT.Zhang
 * Date   2017/10/30
 */

public class Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.IView view;
    private MvpContract.IModel model;
    public Presenter(MvpContract.IView iView){
        view = iView;
        model = new Model();
    }

    public void fetchData(final String category, int dataCount, int pageCount){
        Log.i(TAG, "fetchData: ");
        Observable<CategoryEntity> gankDailyDataEntityObservable = model.fetchData(category, dataCount, pageCount);
        gankDailyDataEntityObservable.subscribe(new Action1<CategoryEntity>() {
            @Override
            public void call(CategoryEntity categoryEntity) {
                Log.i(TAG, "call: " + categoryEntity);
                if (!categoryEntity.isError())
                view.onLoadDataSuccess(categoryEntity.getResults());
            }
        });
    }
}
