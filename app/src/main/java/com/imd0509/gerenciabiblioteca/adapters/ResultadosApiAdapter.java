package com.imd0509.gerenciabiblioteca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Livro;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Item;
import com.imd0509.gerenciabiblioteca.model.apiresponse.VolumeInfo;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ResultadosApiAdapter extends RecyclerView.Adapter<ResultadosApiAdapter.ResultadosViewHolder> {

    private List<Item> listaResultados;

    public ResultadosApiAdapter(List<Item> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public ResultadosApiAdapter(){}

    public void setList(List<Item> listaResultados) {
        this.listaResultados = listaResultados;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultadosApiAdapter.ResultadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View resultadoItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultado_api_item, parent, false);
        return new ResultadosApiAdapter.ResultadosViewHolder(resultadoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadosApiAdapter.ResultadosViewHolder holder, int position) {
        Item item = listaResultados.get(position);
        holder.tvTitulo.setText(item.getVolumeInfo().title);

        if(item.getVolumeInfo().imageLinks != null) {
            Picasso.get()
                    .load(item.getVolumeInfo().imageLinks.thumbnail)
                    .placeholder(R.drawable.capa_livro_shape)
                    .error(R.drawable.capa_livro_shape)
                    .into(holder.tvCapa);
        }


    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }

    public class ResultadosViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitulo;
        ImageView tvCapa;
        public ResultadosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.resultado_api_item_tv_titulo);
            tvCapa = itemView.findViewById(R.id.resultado_api_item_iv_capa);
        }
    }
}

/*
* TextView tvTitulo;
        TextView tvAutor;
        TextView tvPublicacao;
        TextView tvQuantidade;

        LivroListener itemListener;

        public LivrosViewHolder(@NonNull View itemView, LivroListener livroListener) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_titulo);
            tvAutor = itemView.findViewById(R.id.tv_autor);
            tvPublicacao = itemView.findViewById(R.id.tv_publicacao);
            tvQuantidade = itemView.findViewById(R.id.tv_quantidade);

            itemListener = livroListener;

            itemView.setOnClickListener(this);
        }
*
*
* */
