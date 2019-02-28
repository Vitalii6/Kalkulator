package damian.justyna.calculator;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class History extends AppCompatActivity {

    DatabaseHelper myDb;
    TextView lastOperations;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);


        myDb = new DatabaseHelper(this);
        Cursor walker = myDb.getAllData();
        if (walker.getCount() == 0) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("LATEST \n\n");
        while(walker.moveToNext()&&i<6) {
            buffer.append("ID: " + i + "\n");
            buffer.append("Calculation: " + walker.getString(1) + "\n\n");
            i++;
        }
        lastOperations = findViewById(R.id.lastoperations);
        lastOperations.setMovementMethod(new ScrollingMovementMethod());
        lastOperations.append(buffer.toString());
    }

    public void clean(View view) {
        myDb.deleteData();
        Toast.makeText(this, "Cleaned!", Toast.LENGTH_LONG).show();
        lastOperations.setText("none");

    }
}