package fvi.at.ua.notepad;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class NoteActivity extends AppCompatActivity {

    private EditText mEtTitle, mEtContent;
    private String mNoteFileName;
    private Note mLoadNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mEtTitle = (EditText) findViewById(R.id.note_et_title);
        mEtContent = (EditText) findViewById(R.id.note_et_content);

        mNoteFileName = getIntent().getStringExtra("NOTE_FILE");
        if (mNoteFileName != null && !mNoteFileName.isEmpty()) {
            mLoadNote = Utilities.getNoteByName(this, mNoteFileName);
            if (mLoadNote != null) {
                mEtTitle.setText(mLoadNote.getmTitle());
                mEtContent.setText(mLoadNote.getmContent());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_note_save:
                saveNote();
                break;
            case R.id.action_note_delete:
                deleteNote();
                break;
        }
        return true;
    }



    private void saveNote() {
        Note note;
        if (mLoadNote == null) {
            note = new Note(System.currentTimeMillis(), mEtTitle.getText().toString(), mEtContent.getText().toString());
        } else {
            note = new Note(mLoadNote.getmDateTime(), mEtTitle.getText().toString(), mEtContent.getText().toString());
        }

        if (Utilities.saveNote(this, note)) {
            Toast.makeText(this, "note is saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "can not save the note", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    private void deleteNote() {
        if (mLoadNote == null) {
            finish();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("delete")
                    .setMessage("You are about to delete " + mEtTitle.getText().toString() + ", are you  sure?" )
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteNote(getApplicationContext(), mLoadNote.getmDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext(), mEtTitle.getText().toString() + " is delete", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("no", null)
                    .setCancelable(false);

            dialog.show();


        }
    }

}