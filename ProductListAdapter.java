package com.example.meher.Inventory;

/**
 * Created by home on 06/08/2017.
 */



        import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {

    ArrayList<Product> list = new ArrayList<Product>();
    Context context;

    final int REQUEST_CODE_GALLERY = 9999;

    ImageView ivCurImg = null;
    String strPath = null;

    boolean bSearchFlag = false;

    public ProductListAdapter(ArrayList<Product> list, Context context, boolean flag) {
        this.list = list;
        this.context = context;
        this.bSearchFlag = flag;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Product getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if ( view == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_product, null);
        }

        final Product obj = list.get(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(obj.id));

        final EditText edtName = (EditText) view.findViewById(R.id.edtRowName);
        edtName.setText(obj.name);

        final EditText edtDescription = (EditText) view.findViewById(R.id.edtRowDescription);
        edtDescription.setText(obj.description);

        final EditText edtQuantity = (EditText) view.findViewById(R.id.edtRowQuantity);
        edtQuantity.setText(obj.quantity);

        final EditText edtPrice = (EditText) view.findViewById(R.id.edtRowPrice);
        edtPrice.setText(obj.price);

        final Button btnUpdate;
        final Button btnDelete;
        final ImageView ivImage;

        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        ivImage = (ImageView) view.findViewById(R.id.ivRowImage);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( btnUpdate.getText().equals("UPDATE")) {
                    edtName.setEnabled(true);
                    edtDescription.setEnabled(true);
                    edtQuantity.setEnabled(true);
                    edtPrice.setEnabled(true);

                    btnUpdate.setText("SAVE");
                    btnDelete.setText("CANCEL");
                } else {
                    edtName.setEnabled(false);
                    edtDescription.setEnabled(false);
                    edtQuantity.setEnabled(false);
                    edtPrice.setEnabled(false);

                    ProductMainActivity.sqLiteHelper.updateProduct(
                            obj.id,
                            edtName.getText().toString().trim(),
                            edtDescription.getText().toString().trim(),
                            edtQuantity.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            imageViewToByte(ivImage),
                            strPath);


                    Toast.makeText(context, strPath, Toast.LENGTH_SHORT).show();

                    btnUpdate.setText("UPDATE");
                    btnDelete.setText("DELETE");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnDelete.getText().equals("DELETE")) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    ProductMainActivity.sqLiteHelper.deleteProduct(obj.id);
                                    list.remove(position);
                                    notifyDataSetChanged();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                } else {
                    edtName.setEnabled(false);
                    edtDescription.setEnabled(false);
                    edtQuantity.setEnabled(false);
                    edtPrice.setEnabled(false);

                    btnUpdate.setText("UPDATE");
                    btnDelete.setText("DELETE");
                }
            }
        });

        ivImage.setImageBitmap( BitmapFactory.decodeByteArray(obj.image, 0, obj.image.length));
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !btnUpdate.getText().equals("UPDATE")) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");

                    if ( bSearchFlag )
                        ((SearchListActivity)context).startActivityForResult(intent, 9999);
                    else
                        ((ProductListActivity)context).startActivityForResult(intent, 9999);

                    ivCurImg = ivImage;
                }
            }
        });

        return view;
    }

    public void setProductImage(Bitmap bmp) {
        if ( ivCurImg != null ) {
            ivCurImg.setImageBitmap(bmp);
        }
    }

    public void setImagePath(String path) {
        strPath = path;
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
