package fvi.at.ua.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewNotes = (ListView)findViewById(R.id.mListViewNotes);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_note:
               //start NoteActivity in New note mode
               // Intent i = new Intent(this, NoteActivity.class);
                startActivity(new Intent(this, NoteActivity.class ));
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);

        ArrayList<Note> notes = Utilities.getAllSavedNotes(this);

        if(notes == null || notes.size() == 0){
            Toast.makeText(this, "You have no saved notes!",Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteAdapter noteAdapter = new NoteAdapter(this, R.layout.item_note, notes );
            mListViewNotes.setAdapter(noteAdapter);

            mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fileName = ((Note)mListViewNotes.getItemAtPosition(position)).getmDateTime() + Utilities.FILE_EXTENSION;

                    Intent i = new Intent(getApplicationContext(), NoteActivity.class);
                    i.putExtra("NOTE_FILE", fileName);
                    startActivity(i);
                }
            });
        }
    }
}
