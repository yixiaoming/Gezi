package org.miles.gezi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import java.util.List;

public class BottomNavigationLayout extends RadioGroup {

    private List<NavigationItem> mNavigationItems;

    public BottomNavigationLayout(Context context) {
        super(context);
    }

    public BottomNavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addItems(List<NavigationItem> items) {
        mNavigationItems = items;
    }

    public class NavigationItem {
        public String text;
        public Object icon;
    }
}
