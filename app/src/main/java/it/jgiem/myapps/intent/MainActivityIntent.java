package it.jgiem.myapps.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import it.jgiem.myapps.databinding.ActivityMainIntentBinding;


public class MainActivityIntent extends AppCompatActivity {

    private ActivityMainIntentBinding binding;
    public static final String TAG = "MyTag";
    public static final int REQUEST_CODE = 123;
    public static final int SELECT_IMAGE_REQUEST_CODE = 234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainIntentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initListeners();

    }

    private void initListeners() {
        binding.iv.setOnClickListener(v ->{
            onImplicitForResult(null);
        });
    }

    public void onExplicit(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    public void onExplicitForResult(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra("name", binding.tv.getText().toString());
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onImplicit(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
        startActivity(intent);
    }

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult activityResult) {
            if (activityResult.getResultCode() == RESULT_OK && activityResult.getData() != null){
                Uri uri = activityResult.getData().getData();
                binding.iv.setImageURI(uri);
            }
        }
    });

    public void onImplicitForResult(View view) {
       if (false){
           //new way to select image from gallery
           var intent = new Intent(Intent.ACTION_PICK);
           intent.setType("image/*");
           launcher.launch(intent);
       } else {
           //old way to select image from gallery
           var intent = new Intent(Intent.ACTION_GET_CONTENT);
           intent.setType("image/*");
           startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE);
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK ){
            if(requestCode == REQUEST_CODE){
                String text = data.getStringExtra("text");
                binding.tv.setText(text);
            } else if (requestCode == SELECT_IMAGE_REQUEST_CODE){
                Uri uri = data.getData();
                binding.iv.setImageURI(uri);
            }

        }
    }

}