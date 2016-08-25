package com.example.nishad.tourmate.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nishad.tourmate.R;
import com.example.nishad.tourmate.constant.Constants;
import com.example.nishad.tourmate.database.PhotoMomentDataSource;
import com.example.nishad.tourmate.model.PhotoMoment;

import java.io.ByteArrayOutputStream;

public class AddMomentPhotoActivity extends AppCompatActivity {

    private PhotoMomentDataSource photoMomentDataSource;
    private int eventId;
    private ImageView ivPhoto;
    private EditText etCaption;
    private Bitmap yourImage = null;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moment_photo);

        photoMomentDataSource = new PhotoMomentDataSource(this);
        eventId = getIntent().getIntExtra(Constants.EVENT_ID, 0);

        findView();

        // Disable the image view if camera is not available
        if (!hasCamera())
            ivPhoto.setEnabled(false);
    }

    private void findView() {
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        etCaption = (EditText) findViewById(R.id.etCaption);
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

    public void save(View view) {
        String caption = etCaption.getText().toString();

        if (yourImage != null) {
            if (!caption.equals("")) {
                // convert bitmap to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte imageInByte[] = stream.toByteArray();


                Log.d("Insert: ", "Inserting ..");
                boolean inserted = photoMomentDataSource.addPhotoMoment(new PhotoMoment(caption,
                        imageInByte, eventId));
                if (inserted) {
                    Toast.makeText(this, "Your moment inserted successfully", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(this, "Moment insertion failed", Toast.LENGTH_SHORT).show();
                }
                this.finish();
            } else {
                Toast.makeText(this, "Enter a caption", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Capture a photo to save", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View view) {
        this.finish();
    }
}