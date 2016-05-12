// Generated code from Butter Knife. Do not modify!
package com.android.HuoBiAssistant.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AboutActivity$$ViewBinder<T extends com.android.HuoBiAssistant.ui.activity.AboutActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492950, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131492950, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
  }
}
