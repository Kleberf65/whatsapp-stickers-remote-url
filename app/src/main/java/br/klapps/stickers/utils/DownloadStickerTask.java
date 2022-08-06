package br.klapps.stickers.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import br.klapps.stickers.R;
import br.klapps.stickers.model.StickerListModel;
import br.klapps.stickers.model.StickerModel;

@SuppressWarnings("deprecation")
public class DownloadStickerTask extends AsyncTask<StickerListModel, Integer, Boolean> {

    private final WeakReference<Activity> reference;
    private ProgressDialog dialog;

    public DownloadStickerTask(WeakReference<Activity> reference) {
        this.reference = reference;
    }

    @Override
    protected void onPreExecute() {
        if (reference.get() != null) {
            Context context = reference.get();
            dialog = new ProgressDialog(context);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(context.getString(R.string.download_please));
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    @Override
    protected Boolean doInBackground(StickerListModel... stickerListModels) {
        try {

            StickerListModel model = stickerListModels[0];
            List<StickerModel> stickerModels = model.getStickers();

            createImageStorage(model.getTryIcon(), getNameFromUrl(model.getTryIcon()), model.getIdentifier());

            for (int i = 0; i < stickerModels.size(); i++) {
                StickerModel item = stickerModels.get(i);

                createImageStorage(item.getImageUrl(), getNameFromUrl(item.getImageUrl()), model.getIdentifier());

            }
            return (true);
        } catch (Exception e) {
            return (false);
        }

    }

    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private void createImageStorage(String url, String imageName, String identifier) throws IOException {
        URLConnection connection = new URL(url)
                .openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();

        saveImagesStickers(imageName, identifier, inputStream);
        inputStream.close();
    }

    private void saveImagesStickers(String name, String identifier, InputStream inputStream) {
        try {
            String stickerPath = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                    .concat("/Stickers");
            File folder = new File(stickerPath + "/" + identifier + "/" + name);
            FileUtils.copyToFile(inputStream, folder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (dialog != null) dialog.dismiss();
        if (reference.get() != null) {
            Context context = reference.get();
            Toast.makeText(context, success ? R.string.download_success
                    : R.string.download_failure, Toast.LENGTH_SHORT).show();
        }
    }

}