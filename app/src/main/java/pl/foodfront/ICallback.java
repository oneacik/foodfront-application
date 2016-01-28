package pl.foodfront;

import pl.foodfront.serialized.Spots;

/**
 * Created by Michał Stobiński on 2016-01-24.
 */
public interface ICallback {
    void loginInfo(Boolean logged, String msg);
    void invokeSpots(Spots spots);
}
