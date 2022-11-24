package de.dhbw.ka.se.fibo.ui.home;


import static java.security.AccessController.getContext;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.dhbw.ka.se.fibo.Helpers;
import de.dhbw.ka.se.fibo.R;
import de.dhbw.ka.se.fibo.models.Cashflow;
import de.dhbw.ka.se.fibo.models.CashflowType;

public class ListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Cashflow> cashflowArrayList;
    private Context context;

    public ListAdapter(Context context, ArrayList<Cashflow> cashflowArrayList) {
        this.cashflowArrayList = cashflowArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Cashflow cashflow = this.getItem(position);
        CashflowType cashflowType = cashflow.getType();
        BigDecimal overallValue = cashflow.getOverallValue();

        holder.cardTitle.setText(cashflowArrayList.get(position).getName());
        holder.cardTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        holder.imageView.setText(String.valueOf(cashflowArrayList.get(position).getCategory().getName().charAt(0)).toUpperCase());
        holder.cashFlowValue.setText(cashflowType.getSign() + Helpers.formatBigDecimalCurrency(overallValue));
        holder.cashFlowValue.setTextColor(context.getResources().getColor(cashflowType.getColor()));

        holder.cashFlowValue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Format formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        holder.date.setText(formatter.format(cashflowArrayList.get(position).getTimestamp()));

    }

    private Cashflow getItem(int position) {

        return cashflowArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return cashflowArrayList.size();
    }
}
