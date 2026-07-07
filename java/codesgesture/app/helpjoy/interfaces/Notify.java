package codesgesture.app.helpjoy.interfaces;

import codesgesture.app.helpjoy.Model.CartModel;

public interface Notify {
    void onAdd(CartModel data);
    void onRemove(CartModel data);
}
