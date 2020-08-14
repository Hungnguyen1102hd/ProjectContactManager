package com.example.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.projectcontactmanager.R;

import java.util.List;

import vn.hungnguyen.Contact;

import static com.example.projectcontactmanager.R.id.txtTen;

public class ContactAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List objects;
    Contact contact;

    public ContactAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView txtTen = row.findViewById(R.id.txtTen);
        TextView txtPhone = row.findViewById(R.id.txtSoDT);
        ImageButton btnCall = row.findViewById(R.id.btnCall);
        ImageButton btnSms = row.findViewById(R.id.btnSms);
        ImageButton btnDelete = row.findViewById((R.id.btnDelete));

        Contact contact = (Contact) this.objects.get(position);
        txtTen.setText(contact.getName());
        txtPhone.setText(contact.getPhone());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyCall();
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLySms();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDelete();
            }
        });

        return row;
    }

    private void xuLyDelete() {

    }

    private void xyLySms() {

    }

    private void xuLyCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:"+contact.getPhone());
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.context.startActivity(intent);
    }
}

