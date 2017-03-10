package chubbycat_studio.com.helloworld;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHello;
    EditText etHello;
    Button btnCopy;

    /* Mash Up #1 */
    EditText etText1;
    EditText etText2;
    TextView tvResult;
    Button btnCalculate;

    /* Mash Up #2 */
    RadioGroup rbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(this, "W="+width+" H="+height, Toast.LENGTH_SHORT).show();
    }

    private void initInstances() {
        tvHello = (TextView)findViewById(R.id.tvHello);
        etHello = (EditText)findViewById(R.id.etHello);

        etHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    tvHello.setText(etHello.getText());
                    return true;
                }
                return false;
            }
        });

        btnCopy = (Button)findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        /* Mash Up #1 : Start */
        etText1 = (EditText)findViewById(R.id.etText1);
        etText2 = (EditText)findViewById(R.id.etText2);
        tvResult = (TextView)findViewById(R.id.tvResult);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

        /* Mash Up #2 */
        rbg = (RadioGroup)findViewById(R.id.rbgOperator);
    }

    @Override
    public void onClick(View v) {
        if(v == btnCopy) {
            tvHello.setText(etHello.getText());
        } else if(v == btnCalculate) {
            int num1 = 0;
            int num2 = 0;
            int result = 0;

            try {
                num1 = Integer.parseInt(etText1.getText().toString());
            } catch (NumberFormatException e) {
                Log.d("MyApp", "Error 1 : "+e.getMessage());
            }

            try {
                num2 = Integer.parseInt(etText2.getText().toString());
            } catch (NumberFormatException e) {
                Log.d("MyApp", "Error 2 : "+e.getMessage());
            }

            switch (rbg.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    result = doPlus(num1, num2);
                    break;

                case R.id.rbMinus:
                    result = doMinus(num1, num2);
                    break;

                case R.id.rbMultiply:
                    result = doMultiply(num1, num2);
                    break;

                case R.id.rbDivide:
                    result = doDivide(num1, num2);
                    break;
            }
            tvResult.setText("= " + String.valueOf(result));
        }
    }

    private int doPlus(int num1, int num2) {
        return num1 + num2;
    }

    private int doMinus(int num1, int num2) {
        return num1 - num2;
    }

    private int doMultiply(int num1, int num2) {
        return num1 * num2;
    }

    private int doDivide(int num1, int num2) {
        try {
            return num1 / num2;
        } catch (ArithmeticException e) {
            Log.d("MyApp", "Error while divide : " + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
