package com.imd0509.gerenciabiblioteca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Emprestimo;

import java.util.List;

public class EmprestimosAdapter extends RecyclerView.Adapter<EmprestimosAdapter.MinhaViewHolder> {

    private List<Emprestimo> meusEmprestimos;

    public EmprestimosAdapter (List<Emprestimo> lista){
        meusEmprestimos = lista;
    }

    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_emprestimo, parent, false);

        return new MinhaViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        Emprestimo emprestimo = meusEmprestimos.get(position);
        holder.tvNome.setText(emprestimo.getNome());
        holder.tvUsuario.setText(emprestimo.getUsuarioId());
        holder.tvLivro.setText(emprestimo.getBookId());
        holder.tvData.setText(emprestimo.getDataEmprestimo());
        holder.tvDataDevolucao.setText(emprestimo.getDataDevolucao());

    }

    @Override
    public int getItemCount() {
        return meusEmprestimos.size();
    }



    public class MinhaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        CardView cvEmprestimo;
        TextView tvNome;
        TextView tvUsuario;
        TextView tvLivro;
        TextView tvData;
        TextView tvDataDevolucao;

        public MinhaViewHolder(View itemList) {
            super(itemList);

            cvEmprestimo = itemList.findViewById(R.id.cvEmprestimo);
            tvNome = itemList.findViewById(R.id.tvNome);
            tvUsuario = itemList.findViewById(R.id.tvUsuario);
            tvLivro = itemList.findViewById(R.id.tvLivro);
            tvData = itemList.findViewById(R.id.tvData);
            tvDataDevolucao = itemList.findViewById(R.id.tvDataDevolucao);

            cvEmprestimo.setOnClickListener(this);
            cvEmprestimo.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.clicouNoEmprestimo(getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listener.pressionouEmprestimo(getLayoutPosition());
            return true;
        }
    }

    public  interface AoClicarNoItem {
        public void clicouNoEmprestimo(int pos);
        public void pressionouEmprestimo(int pos);
    }

    public AoClicarNoItem listener;

    public void implementaAoClicarNoItem(AoClicarNoItem listener){
        this.listener = listener;
    }

}
