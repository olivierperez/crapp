package fr.o80.sample.timesheet.presentation.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.o80.sample.timesheet.R;
import fr.o80.sample.timesheet.data.entity.TimeEntry;

/**
 * @author Olivier Perez
 */
public class TimesheetAdapter extends RecyclerView.Adapter<TimesheetAdapter.EntryViewHolder> {

    private List<TimeEntry> items;

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timesheet, parent);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 1 : items.size();
    }

    protected class EntryViewHolder extends RecyclerView.ViewHolder {

        public EntryViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(TimeEntry timeEntry) {

        }
    }
}
