package com.imd0509.gerenciabiblioteca.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Livro;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LivrosAdapter extends RecyclerView.Adapter<LivrosAdapter.LivrosViewHolder> {
    private List<Livro> listaLivros;
    private LivroListener livroListener;

    public LivrosAdapter(List<Livro> listaLivros, LivroListener livroListener) {
        this.listaLivros = listaLivros;
        this.livroListener = livroListener;
    }

    public void setList(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LivrosAdapter.LivrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View livroItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.livro_lista_item, parent, false);
        return new LivrosViewHolder(livroItem, livroListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LivrosAdapter.LivrosViewHolder holder, int position) {
        Livro livro = listaLivros.get(position);
        //holder.tvTitulo.setText(livro.getTitulo());
        //holder.tvAutor.setText(livro.getAutores());
        //holder.tvPublicacao.setText("...");
        holder.tvId.setText("" + livro.getId());
        if(livro.getDisponibilidade()) {
            holder.tvDisponibilidade.setText("Disponivel");
            holder.tvDisponibilidade.setTextColor(Color.parseColor("#bdbdbd"));
        }
        else {
            holder.tvDisponibilidade.setText("Emprestado");
            holder.tvDisponibilidade.setTextColor(Color.parseColor("#bdbdbd"));
        }

        if(!livro.getUrlImagemCapa().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(livro.getUrlImagemCapa())
                    .placeholder(R.drawable.capa_livro_shape)
                    .error(R.drawable.capa_livro_shape)
                    .into(holder.ivCapa);
        }
    }

    @Override
    public int getItemCount() {
        return listaLivros.size();
    }

    public class LivrosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //TextView tvTitulo;
        //TextView tvAutor;
        //TextView tvPublicacao;
        //TextView tvQuantidade;
        ImageView ivCapa;
        TextView tvId;
        TextView tvDisponibilidade;

        LivroListener itemListener;

        public LivrosViewHolder(@NonNull View itemView, LivroListener livroListener) {
            super(itemView);
            //tvTitulo = itemView.findViewById(R.id.tv_titulo);
            //tvAutor = itemView.findViewById(R.id.tv_autor);
            //tvPublicacao = itemView.findViewById(R.id.tv_publicacao);
            //tvQuantidade = itemView.findViewById(R.id.tv_quantidade);

            ivCapa = itemView.findViewById(R.id.livro_lista_item_iv_capa);
            tvId = itemView.findViewById(R.id.livro_lista_item_tv_id);
            tvDisponibilidade = itemView.findViewById(R.id.livro_lista_item_tv_disponibilidade);

            itemListener = livroListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.onLivroClickListener(getAdapterPosition());
        }
    }

    public interface LivroListener {
        void onLivroClickListener(int position);
    }
}
