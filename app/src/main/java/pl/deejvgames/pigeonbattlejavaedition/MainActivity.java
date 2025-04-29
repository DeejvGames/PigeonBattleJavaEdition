package pl.deejvgames.pigeonbattlejavaedition;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void infoButtonEvent(View view){
        AlertDialog.Builder infoButtonDialogBuilder = new AlertDialog.Builder(this);
        infoButtonDialogBuilder.setTitle(R.string.info_button);
        infoButtonDialogBuilder.setMessage(getString(R.string.author_name) + " DeejvGames\n" + getString(R.string.source_code) + " Soon...\n" + getString(R.string.original_project_author_name) + " zntsproj\n" + getString(R.string.original_project_source_code) + " https://github.com/zntsproj/pigeonbattle"); // btw you must use getString()
        infoButtonDialogBuilder.setPositiveButton(R.string.info_button_close_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });
        infoButtonDialogBuilder.show();
    }
}