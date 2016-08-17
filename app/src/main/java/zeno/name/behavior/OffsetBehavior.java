package zeno.name.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * @author 陈治谋 (513500085@qq.com)
 * @since 16/8/15
 */
public class OffsetBehavior extends CoordinatorLayout.Behavior
{
  public OffsetBehavior()
  {
  }

  public OffsetBehavior(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  /**
   * 判断使用这个Behavior的View是基于哪一个View的(即要监听哪一个View)
   * 当监听的这个dependency状态改变了,则调用onDependentViewChanged
   */
  @Override public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
  {
    return parent.getContext().getString(R.string.behavior_offset_dependency).equals(dependency.getTag());
  }

  /**
   * 在被监听的View的大小或者位置发生改变时调用， 这个被监听的View是由layoutDependsOn的返回值决定的,
   * 也可以是设置的其他锚点:anchor, 如果这个Behavior改变了child（使用者）的状态，返回true
   */
  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
  {
    int offset = dependency.getTop() - child.getTop();
    ViewCompat.offsetTopAndBottom(child, offset);
    return true;
  }
}
