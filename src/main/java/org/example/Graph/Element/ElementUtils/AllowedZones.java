package org.example.Graph.Element.ElementUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllowedZones  {
    private final static Set<Long> allowedZones = new HashSet<>();
    private final static Long main = 184818L;
    static {
        allowedZones.add(184818L); // jordan
        allowedZones.add(2925596L); // بلقاء
        allowedZones.add(2925597L); // الزرقاء
        allowedZones.add(2925599L); // جرش
        allowedZones.add(2925608L); // المفرق
        allowedZones.add(2925616L); //عجلون
        allowedZones.add(2925617L);// اربد
        allowedZones.add(2926344L); // عمان
        allowedZones.add(2926345L); // العقبه
        allowedZones.add(2926346L); // الكرك
        allowedZones.add(2926347L); //معان
        allowedZones.add(2926348L); // مادبا
        allowedZones.add(2926349L); // الطفيله

    }

    public  static boolean contains(long zoneId) {
        return allowedZones.contains(zoneId);
    }
    public static Long getMain() {
        return main;
    }

}
