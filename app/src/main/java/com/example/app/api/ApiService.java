package com.example.app.api;


import com.example.app.bean.CategoryEntity;
import com.example.app.bean.GankDailyDataEntity;
import com.example.app.bean.GankDailyTitleEntity;
import com.example.app.bean.GankTotalHistoryEntity;
import com.example.app.bean.GitHubUserEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by handsomeyang on 2017/7/6.
 */

public interface ApiService {

    /**
     * 请求某天gank数据
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 数据
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankDailyDataEntity> getDailyData(
            @Path("year") int year, @Path("month") int month, @Path("day") int day);

    /**
     * 获取所有gank历史
     *
     * @return 历史日期
     */
    @GET("day/history")
    Observable<GankTotalHistoryEntity> getTotalHistory();

    /**
     * 获取某几天的干货
     *
     * @param dataCount 干货数量
     * @param pageCount 第几页
     * @return 为了获取标题
     */
    @GET("history/content/{dataCount}/{pageCount}")
    Observable<GankDailyTitleEntity> getDailyTitle(
            @Path("dataCount") int dataCount, @Path("pageCount") int pageCount);

    /**
     * 获取制定种类的内容
     *
     * @param category  类别
     * @param dataCount 数据条数
     * @param pageCount 第几页
     * @return 指定类别内容
     */
    @GET("data/{category}/{dataCount}/{pageCount}")
    Observable<CategoryEntity> getSomeCategory(
            @Path("category") String category, @Path("dataCount") int dataCount,
            @Path("pageCount") int pageCount);

    /**
     * 搜索API
     *
     * @param query    查询的内容
     * @param category 查询的类别  category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 |
     *                 App
     * @param count    count 最大 50
     * @param page     第几页
     * @return 查询结果
     */
    @GET("search/query/{query}/category/{category}/count/{count}/page/{page}")
    Observable<CategoryEntity> getSearchResult(@Path("query") String query,
                                             @Path("category") String category, @Path("count") int count, @Path("page") int page);

    /**
     * 获取个人gihtub 主页数据  主要是头像等
     *
     * @param user user
     * @return 用户信息
     */
    @GET("users/{user}")
    Observable<GitHubUserEntity> getUserInfo(@Path("user") String user);
}
