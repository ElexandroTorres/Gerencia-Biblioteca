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
    private LivroListener livroListener;

    public LivrosAdapter(List<Livro> listaLivros, LivroListener livroListener) {
        this.listaLivros = listaLivros;
        this.livroListener = livroListener;
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
        holder.tvTitulo.setText(livro.getTitulo());
        holder.tvAutor.setText(livro.getAtores().toString());
        holder.tvPublicacao.setText(livro.getPublicadora() + " - " + livro.getDataPublicação());
    }

    @Override
    public int getItemCount() {
        return listaLivros.size();
    }

    public class LivrosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitulo;
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

        @Override
        public void onClick(View v) {
            itemListener.onLivroClickListener(getAdapterPosition());
        }
    }

    public interface LivroListener {
        void onLivroClickListener(int position);
    }
}
