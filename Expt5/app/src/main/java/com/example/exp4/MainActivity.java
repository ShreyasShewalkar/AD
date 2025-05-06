package com.example.exp4;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button contextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonShowAlertDialog).setOnClickListener(v -> showAlertDialog());
        findViewById(R.id.buttonShowProgressDialog).setOnClickListener(v -> showProgressDialog());
        findViewById(R.id.buttonShowPopupMenu).setOnClickListener(this::showPopupMenu);

        contextBtn = findViewById(R.id.buttonShowContextMenu);
        registerForContextMenu(contextBtn);

        // Optional: Add long click listener if menu is not showing
        contextBtn.setOnLongClickListener(v -> {
            openContextMenu(v); // this triggers the context menu manually
            return true;
        });
    }

    void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Alert Dialog")
                .setMessage("Do you want to continue?")
                .setPositiveButton("Yes", (d, w) -> toast("Yes clicked"))
                .setNegativeButton("No", (d, w) -> toast("No clicked"))
                .show();
    }

    void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "Loading", "Please wait...", true, false);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception ignored) {}
            runOnUiThread(() -> progressDialog.dismiss());
        }).start();
    }

    void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_edit) {
                toast("Edit clicked");
            } else if (item.getItemId() == R.id.action_delete) {
                toast("Delete clicked");
            } else {
                return false;
            }
            return true;
        });
        popup.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, v, info);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_copy) {
            toast("Copy clicked");
        } else if (item.getItemId() == R.id.action_paste) {
            toast("Paste clicked");
        } else {
            return super.onContextItemSelected(item);
        }
        return true;
    }

    void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
