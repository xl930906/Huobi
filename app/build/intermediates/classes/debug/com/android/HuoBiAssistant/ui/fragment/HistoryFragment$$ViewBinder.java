// Generated code from Butter Knife. Do not modify!
package com.android.HuoBiAssistant.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HistoryFragment$$ViewBinder<T extends com.android.HuoBiAssistant.ui.fragment.HistoryFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492963, "field 'history_rv'");
    target.history_rv = finder.castView(view, 2131492963, "field 'history_rv'");
    view = finder.findRequiredView(source, 2131492962, "field 'sf'");
    target.sf = finder.castView(view, 2131492962, "field 'sf'");
  }

  @Override public void unbind(T target) {
    target.history_rv = null;
    target.sf = null;
  }
}
