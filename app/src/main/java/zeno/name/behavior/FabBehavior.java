package zeno.name.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 陈治谋 (513500085@qq.com)
 * @since 16/8/17
 */
public class FabBehavior extends CoordinatorLayout.Behavior<FloatingActionButton>
{
  int degree = 0;

  public FabBehavior()
  {
  }

  public FabBehavior(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency)
  {
    return dependency instanceof AppBarLayout;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency)
  {
    degree += 10;
    ViewCompat.setRotation(child, degree);
    return super.onDependentViewChanged(parent, child, dependency);
  }

}
