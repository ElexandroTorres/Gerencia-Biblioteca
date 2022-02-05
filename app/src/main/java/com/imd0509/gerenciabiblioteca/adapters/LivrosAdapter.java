package com.imd0509.gerenciabiblioteca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Livro;

import java.util.List;

public class LivrosAdapter extends RecyclerView.Adapter<LivrosAdapter.LivrosViewHolder> {
    private List<Livro> listaLivros;

    public LivrosAdapter(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }
    @NonNull
    @Override
    public LivrosAdapter.LivrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View livroItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.livro_lista_item, parent, false);
        return new LivrosViewHolder(livroItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LivrosAdapter.LivrosViewHolder holder, int position) {
        Livro livro = listaLivros.get(position);
        holder.tvTitulo.setText(livro.getTitulo());
        holder.tvDescricao.setText(livro.getDescricao());
    }

    @Override
    public int getItemCount() {
        return listaLivros.size();
    }

    public class LivrosViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvDescricao;

        public LivrosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_titulo);
            tvDescricao = itemView.findViewById(R.id.tv_descricao);
        }
    }
}
