package zeno.name.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 陈治谋 (513500085@qq.com)
 * @since 16/8/17
 */
public class HideHeadBehavior extends CoordinatorLayout.Behavior
{
  int     offsetTotal = 0;
  boolean scrolling   = false;
  int y;

  public HideHeadBehavior()
  {
  }

  public HideHeadBehavior(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
  {
    return true;
  }


  @Override
  public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
  {
    offset(child, dyConsumed);
  }

  public void offset(View child, int dy)
  {
    int old = offsetTotal;
    int top = offsetTotal - dy;
    top = Math.max(top, -child.getHeight());
    top = Math.min(top, 0);
    offsetTotal = top;
    if (old == offsetTotal) {
      scrolling = false;
      return;
    }
    int delta = offsetTotal - old;
    child.offsetTopAndBottom(delta);
    scrolling = true;
  }

}
