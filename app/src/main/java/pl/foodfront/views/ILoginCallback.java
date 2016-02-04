package pl.foodfront.views;

/**
 * Created by Michał Stobiński on 2016-02-04.
 */
public interface ILoginCallback extends ICallback {
    void loginInfo(Boolean logged, String msg);
}
