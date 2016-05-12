// Generated code from Butter Knife. Do not modify!
package com.android.HuoBiAssistant.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NowFragment$$ViewBinder<T extends com.android.HuoBiAssistant.ui.fragment.NowFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492965, "field 'now_rv'");
    target.now_rv = finder.castView(view, 2131492965, "field 'now_rv'");
    view = finder.findRequiredView(source, 2131492964, "field 'swipe_refresh'");
    target.swipe_refresh = finder.castView(view, 2131492964, "field 'swipe_refresh'");
  }

  @Override public void unbind(T target) {
    target.now_rv = null;
    target.swipe_refresh = null;
  }
}
