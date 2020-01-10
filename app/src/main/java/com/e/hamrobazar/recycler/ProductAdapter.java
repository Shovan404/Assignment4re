package com.e.hamrobazar.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.hamrobazar.R;
import com.e.hamrobazar.model.Products;
import com.e.hamrobazar.url.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Products> productViewList;

    public ProductAdapter(Context context, List<Products> productViewList) {
        this.context = context;
        this.productViewList = productViewList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products productsView=productViewList.get(position);
        holder.tvName.setText(productsView.getName());
        holder.tvType.setText(productsView.getCondition());

        String price=productsView.getPrice();
        holder.tvPrice.setText("Rs."+price);

        //for setting image in recycle view
        String image=productsView.getImage();
        String imgPath= URL.imagePath+image;

        Picasso.get().load(imgPath).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return productViewList.size();
    }

    // ViewHolder
    public class ProductViewHolder extends  RecyclerView.ViewHolder{
        TextView tvName,tvPrice,tvType;
        ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvType=itemView.findViewById(R.id.tvType);
            imgProduct=itemView.findViewById(R.id.imgProduct);
        }
    }

    }

