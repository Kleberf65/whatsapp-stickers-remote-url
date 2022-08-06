package br.klapps.stickers;

import static br.klapps.stickers.whatsapp.StickerContentProvider.CONTENT_PROVIDER_AUTHORITY;
import static br.klapps.stickers.whatsapp.StickerContentProvider.EXTRA_STICKER_PACK_AUTHORITY;
import static br.klapps.stickers.whatsapp.StickerContentProvider.EXTRA_STICKER_PACK_ID;
import static br.klapps.stickers.whatsapp.StickerContentProvider.EXTRA_STICKER_PACK_NAME;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.klapps.stickers.model.StickerListModel;
import br.klapps.stickers.model.StickerModel;
import br.klapps.stickers.ui.adapter.StickerListAdapter;
import br.klapps.stickers.utils.DownloadStickerTask;
import br.klapps.stickers.utils.ItemClickLister;
import br.klapps.stickers.whatsapp.Sticker;
import br.klapps.stickers.whatsapp.StickerPack;

public class MainActivity extends AppCompatActivity implements ItemClickLister<StickerListModel> {

    private static final int REQUEST_PERMISSION_CODE = 1001;
    private static final int PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED;
    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String VALIDATION_ERROR_KEY = "validation_error";

    private final ActivityResultLauncher<Intent> stickerPackResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_CANCELED) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        final String validationError = intent.getStringExtra(VALIDATION_ERROR_KEY);
                        if (validationError != null) {
                            Toast.makeText(this, validationError, Toast.LENGTH_SHORT).show();
                            ((TextView)findViewById(R.id.tv_error)).setText(validationError);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerViewStickerList();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (permissions.length == grantResults.length) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PERMISSION_GRANTED) {
                        Toast.makeText(this, R.string.permission_alert_message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        }
    }

    private void setupRecyclerViewStickerList() {

        RecyclerView rvStickerList = findViewById(R.id.rv_stickers_list);
        rvStickerList.setAdapter(new StickerListAdapter(this));
    }

    private void verifyStoragePermissions() {
        int writePermission = ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        if (writePermission != PERMISSION_GRANTED || readPermission != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    READ_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
        }
    }

    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private void createStickerPack(StickerListModel model) {
        StickerPack stickerPack = new StickerPack(
                model.getIdentifier(),
                model.getTitle(),
                "StickerDev",
                getNameFromUrl(model.getTryIcon()),
                "",
                "abc@gmail.com",
                "",
                "",
                "https://play.google.com/store/apps/details?id=" + getPackageName(),
                model.isAnimated());

        List<Sticker> stickers = new ArrayList<>();
        for(StickerModel it : model.getStickerModels()){
            stickers.add(new Sticker(getNameFromUrl(it.getImageUrl()),
                    new ArrayList<>(Collections.singletonList("\uD83E\uDD70"))));
        }
        stickerPack.setStickers(stickers);
        List<StickerPack> stickerPacks = new ArrayList<>();
        stickerPacks.add(stickerPack);
        Hawk.put("sticker_packs", stickerPacks);
    }

    @Override
    public void onDownloadClick(StickerListModel stickerListModel) {
        verifyStoragePermissions();
        new DownloadStickerTask(new WeakReference<>(this))
                .execute(stickerListModel);
    }

    @Override
    public void onAddedToWhatsappClick(StickerListModel model) {
        createStickerPack(model);
        Intent intent = new Intent();
        intent.setAction("com.whatsapp.intent.action.ENABLE_STICKER_PACK");
        intent.putExtra(EXTRA_STICKER_PACK_NAME, model.getTitle());
        intent.putExtra(EXTRA_STICKER_PACK_ID, model.getIdentifier());
        intent.putExtra(EXTRA_STICKER_PACK_AUTHORITY, CONTENT_PROVIDER_AUTHORITY);
        stickerPackResult.launch(intent);
    }
}