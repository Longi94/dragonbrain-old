package in.dragonbra.dragonbrain.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class CollectionUtils {

    public static <E> List<E> toList(Iterable<E> iter) {
        List<E> list = new ArrayList<>();
        iter.forEach(list::add);
        return list;
    }
}
