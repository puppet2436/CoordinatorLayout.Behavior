package zeno.name.behavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{

  @BindView(R.id.tv1) TextView tv1;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.tv1) public void onClickText(View v)
  {
    //每次点击向下移动50
    ViewCompat.offsetTopAndBottom(v, 50);
  }

  @OnClick(R.id.btn_nested_scroll) public void onClickNestedScroll()
  {
    startActivity(new Intent(this, NestedScrollActivity.class));
  }

  @OnClick(R.id.btn_fab) public void onClickFab()
  {
    startActivity(new Intent(this, FabActivity.class));
  }

  @OnClick(R.id.btn_hide_head) public void onClickHideHead()
  {
    startActivity(new Intent(this, HideHeadActivity.class));
  }

}
