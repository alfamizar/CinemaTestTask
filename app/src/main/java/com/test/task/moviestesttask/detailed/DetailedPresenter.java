package com.test.task.moviestesttask.detailed;

public class DetailedPresenter implements DetailedPresenterInterface {

    private DetailedViewInterface dvi;

    public DetailedPresenter(DetailedViewInterface dvi) {
        this.dvi = dvi;
    }

    @Override
    public void getDetails() {
        dvi.displayDetails();
    }
}
