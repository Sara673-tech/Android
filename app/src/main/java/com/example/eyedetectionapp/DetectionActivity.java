package com.example.eyedetectionapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eyedetectionapp.detection.DetectionApiService;
import com.example.eyedetectionapp.detection.DetectionResponse;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetectionActivity extends AppCompatActivity {

    ImageView leftEyeIV, rightEyeIV;
    MaterialButton btnDetection;
    ProgressDialog progressDialog;
    int checkImageClick = 0;
    int getType = 1;
    String[] classList = {"ODIR", "Eye Flu"};
    private final int REQUEST_IMAGE_CAPTURE = 1, REQUEST_IMAGE_GALLERY = 2;

    private File leftEyeImageFile, rightEyeImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detection2);
        progressDialog = new ProgressDialog(DetectionActivity.this);
        progressDialog.setTitle("please wait..");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        leftEyeIV = findViewById(R.id.leftEyeIV);
        rightEyeIV = findViewById(R.id.rightEyeIV);
        btnDetection = findViewById(R.id.btnDetection);

        leftEyeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkImageClick = 1;
                showAlertDialog();
            }
        });

        rightEyeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkImageClick = 2;
                showAlertDialog();
            }
        });

        btnDetection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftEyeImageFile != null) {
                    if (rightEyeImageFile != null) {
                        sendImageFile(leftEyeImageFile, rightEyeImageFile);
                    } else {
                        Toast.makeText(DetectionActivity.this, "Choose Right Eye Image Before Detection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "Choose Left Eye Image Before Detection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selection");
        builder.setMessage("Choose Image From");
        builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (iCamera.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(iCamera, REQUEST_IMAGE_CAPTURE);
                }

            }
        });
        builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                iGallery.setType("image/*");
                startActivityForResult(iGallery, REQUEST_IMAGE_GALLERY);

            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                try {
                    if (checkImageClick == 1) {
                        leftEyeIV.setImageBitmap(bitmap);
                        leftEyeImageFile = createImageFileFromBitmap(bitmap);
                    } else if (checkImageClick == 2) {
                        rightEyeIV.setImageBitmap(bitmap);
                        rightEyeImageFile = createImageFileFromBitmap(bitmap);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (requestCode == REQUEST_IMAGE_GALLERY) {
                Uri uri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                    image.setImageBitmap(bitmap);
                    if (checkImageClick == 1) {
                        leftEyeIV.setImageBitmap(bitmap);
                        leftEyeImageFile = createImageFileFromUri(uri);
                    } else if (checkImageClick == 2) {
                        rightEyeIV.setImageBitmap(bitmap);
                        rightEyeImageFile = createImageFileFromUri(uri);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public File createImageFileFromBitmap(Bitmap bitmap) throws IOException {
        File imagesDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }

        File imageFile = new File(imagesDir, "my_image" + System.currentTimeMillis() + ".jpg");

        FileOutputStream outputStream = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.flush();
        outputStream.close();

        return imageFile;
    }

    public File createImageFileFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        File tempFile = File.createTempFile("temp", null, getCacheDir());
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        if (inputStream != null) {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        }
        outputStream.close();
        return tempFile;
    }

    DetectionResponse detectionResponse;

    private void sendImageFile(File leftfile, File rightfile) {
        detectionResponse = new DetectionResponse();
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.97:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DetectionApiService service = retrofit.create(DetectionApiService.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), rightfile);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("conjunctivity_model_img", rightfile.getName(), requestFile);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/jpg"), leftfile);
        MultipartBody.Part imagePart2 = MultipartBody.Part.createFormData("edc_model_img", leftfile.getName(), requestFile2);

        service.uploadFile(imagePart, imagePart2).enqueue(new Callback<DetectionResponse>() {
            @Override
            public void onResponse(Call<DetectionResponse> call, Response<DetectionResponse> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    detectionResponse = response.body();
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), ResultScreenActivity.class)
                            .putExtra("RIGHT_CATEGORY", detectionResponse.getConjunctivity_model_response())
                            .putExtra("LEFT_CATEGORY", detectionResponse.getEye_model_response())
                    );
                } else {
                    // Handle error response
                    Log.e("RetrofitError", "Error: ${response.message()}");
                }
            }

            @Override
            public void onFailure(Call<DetectionResponse> call, Throwable t) {
                Toast.makeText(DetectionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
        finishAffinity();

    }
}