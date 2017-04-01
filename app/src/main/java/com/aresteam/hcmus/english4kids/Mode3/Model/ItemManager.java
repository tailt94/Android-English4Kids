package com.aresteam.hcmus.english4kids.Mode3.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trann on 19-Jun-16.
 */
public class ItemManager {
    private List<Item> items;

    public ItemManager() {
        items = new ArrayList<Item>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public int countItemByName(String name) {
        int count = 0;
        int length = items.size();
        for (int i = 0; i < length; i++) {
            if (items.get(i).getName().equalsIgnoreCase(name))
                count = count + 1;
        }

        return count;
    }

    public Item getItemByName(String name) {
        int length = items.size();
        for (int i = length -1 ; i >= 0; i--) {
            if (items.get(i).getName().equalsIgnoreCase(name))
                return items.get(i);
        }
        return null;
    }

    public void remove(Item item)
    {
        items.remove(item);
    }
}
