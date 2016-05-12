// Generated code from Butter Knife. Do not modify!
package com.android.HuoBiAssistant.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BuyFragment$$ViewBinder<T extends com.android.HuoBiAssistant.ui.fragment.BuyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492956, "field 'mTvPrice'");
    target.mTvPrice = finder.castView(view, 2131492956, "field 'mTvPrice'");
    view = finder.findRequiredView(source, 2131492961, "field 'mTvEntrust'");
    target.mTvEntrust = finder.castView(view, 2131492961, "field 'mTvEntrust'");
    view = finder.findRequiredView(source, 2131492957, "field 'mEtBuy'");
    target.mEtBuy = finder.castView(view, 2131492957, "field 'mEtBuy'");
    view = finder.findRequiredView(source, 2131492954, "method 'start'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.start();
        }
      });
    view = finder.findRequiredView(source, 2131492955, "method 'close'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.close();
        }
      });
    view = finder.findRequiredView(source, 2131492958, "method 'submitEntrust'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.submitEntrust();
        }
      });
    view = finder.findRequiredView(source, 2131492959, "method 'showAll'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.showAll();
        }
      });
    view = finder.findRequiredView(source, 2131492960, "method 'clearAll'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clearAll();
        }
      });
  }

  @Override public void unbind(T target) {
    target.mTvPrice = null;
    target.mTvEntrust = null;
    target.mEtBuy = null;
  }
}
