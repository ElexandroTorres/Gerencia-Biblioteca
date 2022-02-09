package com.imd0509.gerenciabiblioteca.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Item;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ResultadosApiAdapter extends RecyclerView.Adapter<ResultadosApiAdapter.ResultadosViewHolder> {

    private List<Item> listaResultados;

    public ResultadosApiAdapter(List<Item> listaResultados) {
        this.listaResultados = listaResultados;
    }

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

        if(item.getVolumeInfo().authors != null) {
            holder.tvAutores.setText(item.getVolumeInfo().getAuthors());
        }

        if(item.getVolumeInfo().publisher != null) {
            if(item.getVolumeInfo().publishedDate != null) {
                holder.tvEditoraAno.setText(item.getVolumeInfo().publisher + ", " + item.getVolumeInfo().publishedDate);
            }
            else {
                holder.tvEditoraAno.setText(item.getVolumeInfo().publisher);
            }
        }
        else {
            if(item.getVolumeInfo().publishedDate != null) {
                holder.tvEditoraAno.setText(item.getVolumeInfo().publishedDate);
            }
        }


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
        TextView tvAutores;
        TextView tvEditoraAno;
        ImageView tvCapa;
        public ResultadosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.resultado_api_item_tv_titulo);
            tvCapa = itemView.findViewById(R.id.resultado_api_item_iv_capa);
            tvAutores = itemView.findViewById(R.id.resultado_api_item_tv_autores);
            tvEditoraAno = itemView.findViewById(R.id.resultado_api_item_tv_editora);
        }
    }
}


