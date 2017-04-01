package com.aresteam.hcmus.english4kids.Mode3.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trann on 19-Jun-16.
 */
public class ItemFactory {
    private static Item[] sampleItems = {new OpenAllItem(), new OpenOneItem()};

    public static List<String> getAllItemName() {
        List<String> names = new ArrayList<String>();
        int length = sampleItems.length;
        for (int i = 0; i < length; i++) {
            names.add(sampleItems[i].getName());
        }
        return names;
    }
    public static Item createItem(String name) {
        int length = sampleItems.length;
        for (int i = 0; i < length; i++) {
            if (sampleItems[i].getName() == name)
                return sampleItems[i].clone();
        }
        return null;
    }
}
