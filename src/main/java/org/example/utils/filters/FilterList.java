package org.example.utils.filters;

import java.util.ArrayList;
import java.util.List;

/** A list of filters that can be applied to a list of objects.
 * Only one filter of each type can be applied at a time.
 * Adding a filter of a type that is already present will replace the old one.
 */
public class FilterList<T> extends ArrayList<Filter<T>> {
    @Override
    public boolean add(Filter<T> filter) {
        List<Filter<T>> toRemove = new ArrayList<>();
        for (var f : this)
            if (f.getClass().equals(filter.getClass()))
                toRemove.add(f);
        removeAll(toRemove);
        return super.add(filter);
    }
}
