package hk.edu.uic.dishordering;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hk.edu.uic.dishordering.Model.DishSubsystem.Dish;

public class AddDishActivity extends AppCompatActivity {

    public final String APP_TAG = "DishOrderingApp";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public final static int PICK_PHOTO_CODE = 1046;
    public String photoFileName = "dish.jpg";

    @Bind(R.id.dish_image_view)
    ImageView mDishImageView;

    @Bind(R.id.appbar)
    AppBarLayout mAppBarLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Bind(R.id.dish_name_text_input_layout)
    TextInputLayout mDishNameTextInputLayout;

    @Bind(R.id.price_text_input_layout)
    TextInputLayout mPriceTextInputLayout;

    @Bind(R.id.departments_spinner)
    Spinner mDepartmentsSpinner;

    @OnClick(R.id.fab)
    void clickFab() {
        onPickPhoto();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        ButterKnife.bind(this);

        setupFloatingLabelError();

        setSupportActionBar(mToolbar);
        setTitle("");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_dish_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_save:
                saveToFirebase();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onLaunchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    public void onPickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                    Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
                    mDishImageView.setImageBitmap(takenImage);
                } else {
                    Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
                }
                break;
            case PICK_PHOTO_CODE:
                if (data != null) {
                    Uri photoUri = data.getData();
                    Bitmap selectedImage = null;
                    try {
                        selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mDishImageView.setImageBitmap(selectedImage);
                }
        }
    }

    public Uri getPhotoFileUri(String fileName) {
        if (isExternalStorageAvailable()) {
            File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdir()) {
                Log.d(APP_TAG, "failed to create directory");
            }

            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    private void saveToFirebase() {

        // TODO: 5/2/16 add image to Firebase

        Firebase firebase = new Firebase("https://dish-ordering.firebaseio.com/menu");
        Firebase dishRef = firebase.push();

        EditText dishNameEditText = mDishNameTextInputLayout.getEditText();
        String dishName = "";
        if (dishNameEditText != null) {
            String value = dishNameEditText.getText().toString();
            if (!checkDishNameValue(value)) {
                dishNameEditText.requestFocus();
                return;
            } else {
                dishName = value;
            }
        }

        EditText priceEditText = mPriceTextInputLayout.getEditText();
        int price = 0;
        if (priceEditText != null) {
            String value = priceEditText.getText().toString();
            if (!checkPriceValue(value)) {
                priceEditText.requestFocus();
                return;
            } else {
                price = Integer.parseInt(value);
            }
        }

        String department = mDepartmentsSpinner.getSelectedItem().toString();

        Dish dish = new Dish(dishName, null, price);
        dishRef.setValue(dish);
        dishRef.child(department).setValue(true);

        finish();
    }

    private void setupFloatingLabelError() {
        EditText dishNameEditText = mDishNameTextInputLayout.getEditText();
        if (dishNameEditText != null) {
            dishNameEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkDishNameValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        EditText priceEditText = mPriceTextInputLayout.getEditText();
        if (priceEditText != null) {
            priceEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 0) {
                        mPriceTextInputLayout.setError("Price should not be empty.");
                        mPriceTextInputLayout.setErrorEnabled(true);
                    } else {
                        mPriceTextInputLayout.setError(null);
                        mPriceTextInputLayout.setErrorEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private boolean checkDishNameValue(String value) {
        if (value.length() == 0) {
            mDishNameTextInputLayout.setError("Dish name should not be empty.");
            mDishNameTextInputLayout.setErrorEnabled(true);
            return false;
        } else {
            mDishNameTextInputLayout.setError(null);
            mDishNameTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkPriceValue(String value) {
        if (value.length() == 0) {
            mPriceTextInputLayout.setError("Price should not be empty.");
            mPriceTextInputLayout.setErrorEnabled(true);
            return false;
        } else {
            mPriceTextInputLayout.setError(null);
            mPriceTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }
}
