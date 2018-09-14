package com.furianrt.itunesmusicapp;

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}