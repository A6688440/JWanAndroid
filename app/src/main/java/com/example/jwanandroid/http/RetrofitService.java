package com.example.jwanandroid.http;

import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.bean.HomeHeadBean;
import com.example.jwanandroid.bean.CollectListBean;
import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.bean.ProjectKindBean;
import com.example.jwanandroid.systematic.SystematicKindBean;
import com.example.jwanandroid.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SJC on 2020/5/27.
 * Describe：
 */
public interface RetrofitService {

    //登录
    @POST("/user/login")
    Observable<UserBean> HttpLogin(@Query("username") String username,
                                   @Query("password") String password);

    //注册
    @POST("/user/register")
    Observable<UserBean> HttpRegister(@Query("username") String username,
                                      @Query("password") String password,
                                      @Query("repassword") String repassword);

    @GET("/user/logout/json")
    Observable<UserBean> HttpExitLogin();

    //首页头部
    @GET("banner/json")
    Observable<HomeHeadBean> HttpHomeHead();

    //首页文章
    @GET("/article/list/{pager}/json")
    Observable<ArticleBean> HttpHome(@Path("pager") int pager);

    //首页搜索
    @POST("/article/query/{pager}/json")
    Observable<ArticleBean> HttpHomeSearch(@Path("pager") int pager,
                                           @Query("k") String searchKey);

    //------------------------------

    //体系分类
    @GET("/tree/json")
    Observable<SystematicKindBean> httpSystematicKind();

    //体系文章
    @GET("/article/list/{pager}/json")
    Observable<ArticleBean> httpSystematicArticle(@Path("pager") int pager,
                                                  @Query("cid") int cid);

    //--------------------------------

    //导航数据
    @GET("/navi/json")
    Observable<NavigationProjectBean> httpNavigation();

    //--------------------------------


    //--------------------------------

    //收藏列表
    @GET("/lg/collect/list/{pager}/json")
    Observable<CollectListBean> httpCollectList(@Path("pager") int Pager);

    //收藏站内文章
    @POST("/lg/collect/{id}/json")
    Observable<ArticleBean> httpCollectN(@Path("id") int cid);


    //收藏站外文章title，author，link
    @POST("/lg/collect/add/json")
    Observable<ArticleBean> httpCollectW(@Query("title") String title,
                                             @Query("author") String author,
                                             @Query("link") String link);

    //取消收藏站内文章1
    @POST("/lg/uncollect_originId/{id}/json")
    Observable<ArticleBean> httpUnCollectOne(@Path("id") int cid);


    //取消收藏站外文章2 id,originId
    @POST("/lg/uncollect/{id}/json")
    Observable<ArticleBean> httpUnCollectTwo(@Path("id") String title,
                                             @Query("originId") String originId);


    //--------------------------------

    //项目分类
    @GET("/project/tree/json")
    Observable<ProjectKindBean> httpProjectKind();

    //项目列表数据
    @GET("/project/list/{pager}/json?cid={cid}")
    Observable<NavigationProjectBean> httpProject(@Path("pager")int pager,
                                                  @Path("cid") int cid);

    //--------------------------------


}
