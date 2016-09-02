package zeno.name.behavior;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <h5>- {@link BottomSheetBehavior} 的使用</h5>
 * <p>
 * In Support Library 24.2.0
 * 必须设置 <pre>android:layout_alignParentBottom="true"</pre> 属性,
 * 或者 BottomSheetBehavior.setPeekHeight(0);
 *
 * @see <a href='http://gold.xitu.io/post/57bfd4c4efa631005aa5b530'>Material之Behavior实现支付宝密码弹窗 仿淘宝/天猫商品属性选择</a>
 */
public class BottomSheetActivity extends AppCompatActivity
{

  @BindView(R.id.btn_toggle) AppCompatButton     btnToggle;
  @BindView(R.id.tab_layout) LinearLayout        tabLayout;
  private                    BottomSheetBehavior behavior;

  private BottomSheetDialog dialog;


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_sheet);
    ButterKnife.bind(this);
    behavior = BottomSheetBehavior.from(tabLayout);
    prepareDialog();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu)
  {
    new MenuInflater(this).inflate(R.menu.bottom_sheet, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId()) {
      case R.id.menu_peek_on:
        behavior.setPeekHeight(dp2px(48));
        break;
      case R.id.menu_peek_off:
        behavior.setPeekHeight(0);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @OnClick(R.id.btn_toggle) public void onClickToggle()
  {
    if (behavior.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
      behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    } else {
      behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
  }

  @OnClick(R.id.btn_toggle_dialog) public void onClickToggleDialog()
  {
    if (dialog.isShowing()) {
      dialog.dismiss();
    } else {
      dialog.show();
    }
  }

  private void prepareDialog()
  {
    dialog = new BottomSheetDialog(this);
    View view = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet, null, false);
    dialog.setContentView(view);
    setBehaviorCallback(dialog);
  }

  /**
   * 系统的BottomSheetDialog是基于BottomSheetBehavior封装的，
   * 这里判断了当我们滑动隐藏了BottomSheetBehavior中的View后，
   * 内部是设置了BottomSheetBehavior的状态为STATE_HIDDEN，
   * 接着它替我们关闭了Dialog，所以我们再次调用dialog.show()
   * 的时候Dialog没法再此打开状态为HIDE的dialog了。
   */
  private void setBehaviorCallback(@NonNull BottomSheetDialog dialog)
  {
    View view = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
    if (view == null) {
      return;
    }

    final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view);
    bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
    {
      @Override
      public void onStateChanged(@NonNull View bottomSheet, int newState)
      {
        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
          dialog.dismiss();
          bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
      }

      @Override
      public void onSlide(@NonNull View bottomSheet, float slideOffset) { }
    });
  }

  public static int dp2px(float dipValue)
  {
    final float scale = Resources.getSystem().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }
}
