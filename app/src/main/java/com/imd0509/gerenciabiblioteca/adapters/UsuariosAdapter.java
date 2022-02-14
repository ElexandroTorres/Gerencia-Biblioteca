package com.imd0509.gerenciabiblioteca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.LCHolder> {
    private List<Usuario> usuarios;
    private ItemUsuarioListener itemUsuarioListener;

    public UsuariosAdapter(List<Usuario> usuarios, ItemUsuarioListener itemUsuarioListener) {
        this.usuarios = usuarios;
        this.itemUsuarioListener = itemUsuarioListener;
    }


    @NonNull
    @Override
    public LCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        LCHolder lcHolder = new LCHolder(item, itemUsuarioListener);
        item.setOnLongClickListener(lcHolder);
        return lcHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LCHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.tvNome.setText(usuario.getNome());
        holder.tvEmprestimosTotal.setText("Total de empréstimos: " + usuario.getEmprestimos());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class LCHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemUsuarioListener itemUsuarioListener;
        public TextView tvNome;
        public TextView tvEmprestimosTotal;

        public LCHolder(@NonNull View itemView, ItemUsuarioListener itemUsuarioListener) {
            super(itemView);

            this.itemUsuarioListener = itemUsuarioListener;

            tvNome = itemView.findViewById(R.id.tv_nome_usuario);
            tvEmprestimosTotal = itemView.findViewById(R.id.tv_emprestimos_total);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemUsuarioListener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            itemUsuarioListener.onLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface ItemUsuarioListener {
        void onItemClick(int position);
        void onLongClick(int position);
    }
}
