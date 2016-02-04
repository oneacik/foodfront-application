package pl.foodfront.views;

import pl.foodfront.serialized.Spots;

/**
 * Created by Michał Stobiński on 2016-01-24.
 */
public interface IMainCallback extends ICallback {
    void invokeSpots(Spots spots);
}
