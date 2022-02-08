package com.imd0509.gerenciabiblioteca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.apiresponse.VolumeInfo;

import java.util.List;

public class ResultadosApiAdapter extends RecyclerView.Adapter<ResultadosApiAdapter.ResultadosViewHolder> {

    private List<VolumeInfo> listaResultados;

    public ResultadosApiAdapter(List<VolumeInfo> listaResultados) {
        this.listaResultados = listaResultados;
    }

    @NonNull
    @Override
    public ResultadosApiAdapter.ResultadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View resultadoItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.livro_lista_item, parent, false);
        return new ResultadosApiAdapter.ResultadosViewHolder(resultadoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadosApiAdapter.ResultadosViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }

    public class ResultadosViewHolder extends RecyclerView.ViewHolder{
        public ResultadosViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
