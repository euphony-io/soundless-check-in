package com.example.soundlesscheck_in;

import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int btnCancel = 11111;
    private boolean isFirst;
    private boolean isBtnMade = false;

    private LinearLayout mLayout;
    private EditText mPhoneNumber;
    private EditText mLivingCity;
    private Button mGetInfoBtn;
    private Button mCancelBtn;

    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do="";
    String choice_se="";
    Spinner spin1;
    Spinner spin2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);

        spin1 = (Spinner) findViewById(R.id.spinner1);
        spin2 = (Spinner)findViewById(R.id.spinner2);
        //final Spinner spin3 = (Spinner)findViewById(R.id.spin_dong);



        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);

        setUI();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isFirst) updateEditTextUI();
    }

    protected void setUI() {
        mLayout = findViewById(R.id.layoutTutorialButtons);
        mPhoneNumber = findViewById(R.id.phonenumberEditText);
        mLivingCity = findViewById(R.id.citynameEditText);
        mGetInfoBtn = findViewById(R.id.btnGetFirstInfo);

        mPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        mGetInfoBtn.setOnClickListener(this);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//첫번째 spinner 클릭시 이벤트 발생입니다. setO 정도까지 치시면 자동완성됩니다. 뒤에도 마찬가지입니다.
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {//제대로 자동완성하셨다면 이부분이 자동으로 만들어 질 것입니다. int i는 포지션이라 하여 제가 spinner에 몇번째걸 선택했는지 값이 들어갑니다. 필요하겠죠? ㅎㅎ
                if (adspin1.getItem(i).equals("서울")) {//spinner에 값을 가져와서 i 보이시나요 제가 클릭 한것이 서울인지 확인합니다.
                    choice_do = "서울";//버튼 클릭시 출력을 위해 값을 넣었습니다.
                    adspin2 = ArrayAdapter.createFromResource(TutorialActivity.this, R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);//서울일 경우에 두번째 spinner에 값을 넣습니다. //그냥 this가 아닌 Main~~~인 이유는 그냥 this는 메인엑티비티인 경우만 가능합니다. //지금과 같이 다른 함수안이나 다른 클래스에서는 꼭 자신의 것을 넣어주어야 합니다.//혹시나 다른 class -> Public View밑에서 작업하시는 분은 view명.getContext()로 해주셔야 합니다.//예로 View rootView =~~ 선언하신 경우에는 rootView.getContext()써주셔야합니다. this가 아니라요.
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);//두번째 어댑터값을 두번째 spinner에 넣었습니다.
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//저희는 두번째 선택된 값도 필요하니 이안에 두번째 spinner 선택 이벤트를 정의합니다.
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();//두번째 선택된 값을 choice_se에 넣습니다.
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                        }
                    });
                } else if (adspin1.getItem(i).equals("인천")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "인천";
                    adspin2 = ArrayAdapter.createFromResource(TutorialActivity.this, R.array.spinner_do_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        isFirst = getIntent().getBooleanExtra("boolean_checkFirst", false);
        if(!isFirst&&!isBtnMade) addNewButton();
    }

    protected void addNewButton() {
        mCancelBtn = new Button(this);
        LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pm.gravity = Gravity.RIGHT;
        mCancelBtn.setId(btnCancel);
        mCancelBtn.setText("CANCEL");
        mCancelBtn.setTextColor(Color.parseColor("#FFFFFF"));   //white
        mCancelBtn.setTextSize(25);
        mCancelBtn.setBackgroundResource(R.drawable.btn_background);
        mCancelBtn.setLayoutParams(pm);
        mLayout.addView(mCancelBtn);
        mCancelBtn.setOnClickListener(this);
        isBtnMade = true;
    }
    protected void updateEditTextUI() {
        mPhoneNumber.setText(EncryptedSPManager.getString(this,"phone"));
        mLivingCity.setText(EncryptedSPManager.getString(this, "city"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetFirstInfo:
                EncryptedSPManager.setString(this, "phone", mPhoneNumber.getText().toString());
                EncryptedSPManager.setString(this, "city", mLivingCity.getText().toString());
                finish();
                break;
            case btnCancel:
                finish();
                break;
            default:
                break;
        }
    }
}
