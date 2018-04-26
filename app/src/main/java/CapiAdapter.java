import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import DataModel.Capo;

public class CapiAdapter extends BaseAdapter {

    private Context context;
    private List<Capo> elencoCapi;

    public CapiAdapter(Context context, List<Capo> elencoCapi) {
        this.context = context;
        this.elencoCapi = elencoCapi;
    }


    @Override
    public int getCount() {
        return elencoCapi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
