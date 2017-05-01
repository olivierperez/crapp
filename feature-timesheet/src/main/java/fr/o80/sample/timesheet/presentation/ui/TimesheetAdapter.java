package fr.o80.sample.timesheet.presentation.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import fr.o80.sample.timesheet.R;
import fr.o80.sample.timesheet.R2;
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

    public void setItems(List<TimeEntry> entries) {
        items = entries;
        notifyItemRangeInserted(0, entries.size());
    }

    protected class EntryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.project_name)
        protected TextView projectName;

        @BindView(R2.id.project_code)
        protected TextView projectCode;

        public EntryViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(TimeEntry timeEntry) {
            projectName.setText(timeEntry.project());
            projectCode.setText(timeEntry.code());
        }
    }
}
