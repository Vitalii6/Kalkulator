package damian.justyna.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDb;
    private EditText inputText;
    private TextView result;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonSum;
    private Button buttonSubstract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonEqual;
    private Button buttonDot;
    private Button buttonLeftBracket;
    private Button buttonRightBracket;
    private Button buttonClear;
    private Button buttonDelete;
    private Button buttonHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        inputText = findViewById(R.id.input);
        myDb = new DatabaseHelper(this);
        initializeButtons();
        setOnClick();
        // Hiding and disable keyboard
        inputText.setRawInputType(InputType.TYPE_NULL);
    }

    private void initializeButtons() {
        this.button0 = findViewById(R.id.button0);
        this.button1 = findViewById(R.id.button1);
        this.button2 = findViewById(R.id.button2);
        this.button3 = findViewById(R.id.button3);
        this.button4 = findViewById(R.id.button4);
        this.button5 = findViewById(R.id.button5);
        this.button6 = findViewById(R.id.button6);
        this.button7 = findViewById(R.id.button7);
        this.button8 = findViewById(R.id.button8);
        this.button9 = findViewById(R.id.button9);
        this.buttonSum = findViewById(R.id.buttonSum);
        this.buttonSubstract = findViewById(R.id.buttonSubstract);
        this.buttonMultiply = findViewById(R.id.buttonMultiply);
        this.buttonDivide = findViewById(R.id.buttonDivide);
        this.buttonEqual = findViewById(R.id.buttonEqual);
        this.buttonDot = findViewById(R.id.buttonDot);
        this.buttonLeftBracket = findViewById(R.id.buttonLeftBracket);
        this.buttonRightBracket = findViewById(R.id.buttonRightBracket);
        this.buttonClear = findViewById(R.id.buttonClear);
        this.buttonDelete = findViewById(R.id.buttonDelete);
        this.buttonHistory = findViewById(R.id.buttonHistory);

    }

    private void setOnClick() {
        this.button0.setOnClickListener(this);
        this.button1.setOnClickListener(this);
        this.button2.setOnClickListener(this);
        this.button3.setOnClickListener(this);
        this.button4.setOnClickListener(this);
        this.button5.setOnClickListener(this);
        this.button6.setOnClickListener(this);
        this.button7.setOnClickListener(this);
        this.button8.setOnClickListener(this);
        this.button9.setOnClickListener(this);
        this.buttonSum.setOnClickListener(this);
        this.buttonSubstract.setOnClickListener(this);
        this.buttonMultiply.setOnClickListener(this);
        this.buttonDivide.setOnClickListener(this);
        this.buttonEqual.setOnClickListener(this);
        this.buttonDot.setOnClickListener(this);
        this.buttonLeftBracket.setOnClickListener(this);
        this.buttonRightBracket.setOnClickListener(this);
        this.buttonClear.setOnClickListener(this);
        this.buttonDelete.setOnClickListener(this);
        this.buttonHistory.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button0:
                append("0");
                break;
            case R.id.button1:
                append("1");
                break;
            case R.id.button2:
                append("2");
                break;
            case R.id.button3:
                append("3");
                break;
            case R.id.button4:
                append("4");
                break;
            case R.id.button5:
                append("5");
                break;
            case R.id.button6:
                append("6");
                break;
            case R.id.button7:
                append("7");
                break;
            case R.id.button8:
                append("8");
                break;
            case R.id.button9:
                append("9");
                break;
            case R.id.buttonSum:
                append("+");
                break;
            case R.id.buttonSubstract:
                append("-");
                break;
            case R.id.buttonMultiply:
                append("*");
                break;
            case R.id.buttonDivide:
                append("/");
                break;
            case R.id.buttonEqual:
                doMath();
                break;
            case R.id.buttonDot:
                append(".");
                break;
            case R.id.buttonLeftBracket:
                append("(");
                break;
            case R.id.buttonRightBracket:
                append(")");
                break;
            case R.id.buttonClear:
                clearInput();
                break;
            case R.id.buttonDelete:
                deleteInput();
                break;
            case R.id.buttonHistory:
                sendMessage();
            default:
                break;
        }
    }

    private void append(String str) {
        this.inputText.getText().append(str);
    }

    private void deleteInput() {
        if (!isEmpty()) {
            this.inputText.getText().delete(getInput().length() - 1, getInput().length());
        }
    }

    private void clearInput() {
        if (!isEmpty()) {
            this.inputText.getText().clear();
        }
    }

    private String getInput() {
        return this.inputText.getText().toString();
    }

    private boolean isEmpty() {
        return getInput().isEmpty();
    }


    public void sendMessage() {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }


    public void doMath() {
        Expression math = new Expression(inputText.getText().toString());
        result.setText(String.valueOf(math.calculate()));
        if (result.getText() != "NaN") {
            if (myDb.insertData(inputText.getText().toString())) {
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(MainActivity.this, "Wrong input!", Toast.LENGTH_LONG).show();
    }
}
