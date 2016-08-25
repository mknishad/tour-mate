package com.example.nishad.tourmate.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.database.EventsDataSource;
import com.example.nishad.tourmate.database.PhotoMomentDataSource;
import com.example.nishad.tourmate.model.PhotoMoment;

import java.io.ByteArrayOutputStream;

public class AddMomentPhotoActivity extends AppCompatActivity {

    private PhotoMomentDataSource photoMomentDataSource;
     private EventsDataSource eventsDataSource;
    private int eventId;
    private TextView tvMessage;
    private ImageView ivPhoto;
    private EditText etCaption;
    private Button btnSave;
    private Button btnCancel;
    private Bitmap yourImage;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moment_photo);

        photoMomentDataSource = new PhotoMomentDataSource(this);
        eventsDataSource = new EventsDataSource(this);
        eventId = getIntent().getIntExtra(Constants.EVENT_ID, 0);

        findView();

        // Disable the image view if camera is not available
        if (!hasCamera())
            ivPhoto.setEnabled(false);
    }

    private void findView() {
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        etCaption = (EditText) findViewById(R.id.etCaption);
        btnSave = (Button) findViewById(R.id.btnSavePhoto);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }

    // Check if the user has a camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // Launch the camera
    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Take a picture and pass result along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    // Return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Get the photo
            Bundle extras = data.getExtras();

            if (extras != null) {
//                Bitmap photo = (Bitmap) extras.get("data");
                yourImage = extras.getParcelable("data");
                ivPhoto.setImageBitmap(yourImage);
            }
        }
    }

    public void Save(View view) {
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        String caption = etCaption.getText().toString();

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        boolean inserted = photoMomentDataSource.addPhotoMoment(new PhotoMoment(caption,
                imageInByte, eventId));
        if (inserted)
            Toast.makeText(this, "Moment inserted", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void cancel(View view) {
        this.finish();
    }
}