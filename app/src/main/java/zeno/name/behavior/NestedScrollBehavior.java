package zeno.name.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>
 * 这个滑动事件是对于CoordinatorLayout的。
 * <p>
 * 所以只要CoordinatorLayout有NestedScrollingChild就会滑动，他滑动就会触发这几个回调。
 * <p>
 * 无论你是否依赖了(监听了)那个View。
 * <ul>
 * <li>{@link #onStartNestedScroll(CoordinatorLayout, View, View, View, int)}</li>
 * <li>{@link #onNestedScroll(CoordinatorLayout, View, View, int, int, int, int)}</li>
 * <li>{@link #onNestedPreFling(CoordinatorLayout, View, View, float, float)}</li>
 * <li>{@link #onNestedFling(CoordinatorLayout, View, View, float, float, boolean)}</li>
 * </ul>
 *
 * @author 陈治谋 (513500085@qq.com)
 * @since 16/8/15
 */
public class NestedScrollBehavior extends CoordinatorLayout.Behavior
{
  public NestedScrollBehavior()
  {
  }

  public NestedScrollBehavior(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  /**
   * @return true if the Behavior wishes to accept this nested scroll
   */
  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
  {
    //垂直方向滑动
    return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
  }

  @Override
  public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
  {
    int scrollY = target.getScrollY();
    child.setScrollY(scrollY);
  }

  @Override
  public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY)
  {
    ((NestedScrollView) child).fling((int) velocityY);
    return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
  }

  @Override
  public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed)
  {
    return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
  }
}
